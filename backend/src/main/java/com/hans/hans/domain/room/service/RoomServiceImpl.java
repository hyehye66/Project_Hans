package com.hans.hans.domain.room.service;

import com.hans.hans.domain.bodygame.dto.BodyGameCreateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameCreateResponseDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationUpdateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationUpdateResponseDto;
import com.hans.hans.domain.mode.repository.ModeRepository;
import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.room.dto.*;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import com.hans.hans.domain.room.repository.RoomMemberRepository;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.member.repository.MemberRepository;
import com.hans.hans.domain.wordgame.dto.WordGameCreateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameCreateResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameUpdateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameUpdateResponseDto;
import com.hans.hans.global.enumerate.Modes;
import com.hans.hans.global.exception.*;
import io.openvidu.java.client.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class RoomServiceImpl implements RoomService{
    // OpenVidu object as entrypoint of the SDK
    private String OPENVIDU_URL;
    // Secret shared with our OpenVidu server
    private String SECRET;
    private OpenVidu openVidu;
    private Map<Long, Session> mapSessions;

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final ModeRepository modeRepository;
    private final RoomMemberRepository roomMemberRepository;

    public RoomServiceImpl(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl, RoomRepository roomRepository,MemberRepository memberRepository ,ModeRepository modeRepository, RoomMemberRepository roomMemberRepository) {

        this.SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);

        mapSessions = new ConcurrentHashMap<>();

        this.roomRepository=roomRepository;
        this.memberRepository=memberRepository;
        this.modeRepository=modeRepository;
        this.roomMemberRepository=roomMemberRepository;
        System.out.println("hans sessioncontroller openvidu.sec=="+secret+", openvidu.url=="+openviduUrl);
    }

    @Override
    public RoomsResponseDto getRooms(RoomGetRequestDto roomGetRequestDto, Pageable pageable) {
        Page<Room> rooms = roomRepository.findRoomsByMode(roomGetRequestDto.toEntity(), pageable);
        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);

        return roomsResponseDto;
    }

    @Override
    public boolean checkEnterRoom(Long roomSequence){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        if(room.getRestrictNum()==room.getCurrentNum() || room.isRoomStatus()) return false;

        return true;
    }

    @Override
    public RoomMemberResponseDto enterRoom(String email, Long roomSequence){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        try{
            if(roomMemberRepository.findByMember(member)==null){
                Room room = roomRepository.findByRoomSequence(roomSequence);

                OpenViduRole role = OpenViduRole.PUBLISHER;
                String serverData = "{\"serverData\": \"" + member.getEmail() + "\"}";
                ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();
                String token = this.mapSessions.get(room.getRoomSequence()).createConnection(connectionProperties).getToken();

                Date now = new Date();

                room.updateCurrentNum(room.getCurrentNum()+1);
                roomRepository.save(room);

                RoomMember roomMember = roomMemberRepository.save(
                        RoomMember.builder()
                                .member(member)
                                .room(room)
                                .token(token)
                                .enterDTTM(now)
                                .build()
                );

                RoomMemberResponseDto roomMemberResponseDto = new RoomMemberResponseDto(roomMember);

                return roomMemberResponseDto;
            }else throw new AlreadyInTheRoomException("현재 사용자는 이미 다른 방에 입장되어 있습니다.");
        }catch (NoSuchElementException e){
            throw new NoExistRoomException("존재하지 않는 방입니다.");
        }catch (OpenViduJavaClientException e) {
            // If internal error generate an error message and return it to client
            throw new SessionEnterException("세션 입장이 불가능합니다.");
        }catch (OpenViduHttpException e) {
            //openvidu  관련 Exception
            // If error generate an error message and return it to client
            if (404 == e.getStatus()) {
                // Invalid sessionId (user left unexpectedly). Session object is not valid
                // anymore. Clean collections and continue as new session
                this.mapSessions.remove(roomSequence);
                Room room = roomRepository.findByRoomSequence(roomSequence);
                roomMemberRepository.deleteAll(roomMemberRepository.findRoomMembersByRoom(room));
                roomRepository.delete(room);
            }
            System.out.println("sessionCreate exception response");
            throw new SessionEnterException("세션 입장이 불가능합니다");
        }
    }

    @Override
    public RoomMemberResponseDto enterRoomByRandom(String email, Modes modes){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));


        List<Room> rooms = roomRepository.findRoomsByModeAndRoomStatus(modeRepository.findByModeSequence(modes.getModeSequence()),false);

        List<Long> availableRooms = new ArrayList<>();

        // 현재 입장 가능한 방의 Room Sequence를 저장
        for(Room room : rooms){
            if (room.getCurrentNum()<room.getRestrictNum()) availableRooms.add(room.getRoomSequence());
        }
        Collections.shuffle(availableRooms);

        Long enterRoomSequence = availableRooms.get(0);

        try{
            if(roomMemberRepository.findByMember(member)==null){
                Room room = roomRepository.findByRoomSequence(enterRoomSequence);

                OpenViduRole role = OpenViduRole.PUBLISHER;
                String serverData = "{\"serverData\": \"" + member.getEmail() + "\"}";
                ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();
                String token = this.mapSessions.get(room.getRoomSequence()).createConnection(connectionProperties).getToken();

                Date now = new Date();

                room.updateCurrentNum(room.getCurrentNum()+1);
                roomRepository.save(room);

                RoomMember roomMember = roomMemberRepository.save(
                        RoomMember.builder()
                                .member(member)
                                .room(room)
                                .token(token)
                                .enterDTTM(now)
                                .build()
                );

                RoomMemberResponseDto roomMemberResponseDto = new RoomMemberResponseDto(roomMember);

                return roomMemberResponseDto;
            }else throw new AlreadyInTheRoomException("현재 사용자는 이미 다른 방에 입장되어 있습니다.");
        }catch (NoSuchElementException e){
            throw new NoExistRoomException("존재하지 않는 방입니다.");
        }catch (IndexOutOfBoundsException e){
            throw new NoExistRoomException("현재 입장하실 수 있는 방이 없습니다.");
        }catch (OpenViduJavaClientException e) {
            // If internal error generate an error message and return it to client
            throw new SessionEnterException("방 입장이 불가합니다.");
        }catch (OpenViduHttpException e) {
            //openvidu  관련 Exception
            // If error generate an error message and return it to client
            if (404 == e.getStatus()) {
                // Invalid sessionId (user left unexpectedly). Session object is not valid
                // anymore. Clean collections and continue as new session
                Room room = roomRepository.findByRoomSequence(enterRoomSequence);
                this.mapSessions.remove(room.getRoomSequence());
                roomMemberRepository.deleteAll(roomMemberRepository.findRoomMembersByRoom(room));
                roomRepository.delete(room);
            }
            System.out.println("sessionCreate exception response");
            throw new SessionEnterException("방 입장이 불가합니다.");
        }
    }

    @Override
    public RoomsResponseDto searchRoomByTitle(String title, Pageable pageable){
        Page<Room> rooms = roomRepository.findRoomsByTitleContaining(title, pageable);
        if(rooms.getContent().size()==0) throw new NoExistRoomSearchByTitleException("현재 검색한 방 제목으로 개설된 방이 없습니다.");

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);

        return roomsResponseDto;
    }

    @Override
    public RoomsResponseDto searchRoomByNickname(String nickname, Pageable pageable){
        Member member = memberRepository.findByNickname(nickname);

        Page<Room> rooms = roomRepository.findRoomsByMember(member,pageable);
        if(rooms.getContent().size()==0) throw new NoExistRoomSearchByNicknameException("현재 검색한 방장 닉네임으로 개설된 방이 없습니다.");

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);

        return roomsResponseDto;
    }

    @Override
    public ConversationCreateResponseDto createConversationRoom(String email, ConversationCreateRequestDto conversationCreateRequestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));

        OpenViduRole role = OpenViduRole.PUBLISHER;
        String serverData = "{\"serverData\": \"" + member.getEmail() + "\"}";
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();

        try {
            Date now = new Date();
            String title = conversationCreateRequestDto.getTitle();
            int restrictNum = conversationCreateRequestDto.getRestrictNum();

            Session session = openVidu.createSession();
            String token = session.createConnection(connectionProperties).getToken();

            Room room = roomRepository.save(
                    Room.builder()
                            .member(member)
                            .mode(modeRepository.findByModeSequence(Modes.TALK.getModeSequence()))
                            .title(title)
                            .restrictNum(restrictNum)
                            .currentNum(1)
                            .roomDTTM(now)
                            .roomStatus(false)
                            .build()
            );

            roomMemberRepository.save(
                    RoomMember.builder()
                            .member(member)
                            .room(room)
                            .token(token)
                            .enterDTTM(now)
                            .build()
            );

            this.mapSessions.put(room.getRoomSequence(), session);

            ConversationCreateResponseDto conversationCreateResponseDto = new ConversationCreateResponseDto(room, token);

            return conversationCreateResponseDto;

        }catch (OpenViduJavaClientException e1) {
            // If internal error generate an error message and return it to client
            throw new SessionCreateException("세션 만드는데 문제있음");
        }catch (OpenViduHttpException e) {
            //openvidu  관련 Exception
            // If error generate an error message and return it to client
            System.out.println("sessionCreate exception response");
            throw new SessionCreateException("세션 만드는데 문제있음");
        }
    }

    @Override
    public WordGameCreateResponseDto createWordGameRoom(String email, WordGameCreateRequestDto wordGameCreateRequestDto){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));

        OpenViduRole role = OpenViduRole.PUBLISHER;
        String serverData = "{\"serverData\": \"" + member.getEmail() + "\"}";
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();
        try {
            Date now = new Date();
            String title = wordGameCreateRequestDto.getTitle();

            int restrictNum = wordGameCreateRequestDto.getRestrictNum();
            int totalQuestion = wordGameCreateRequestDto.getTotalQuestion();
            Session session = openVidu.createSession();
            String token = session.createConnection(connectionProperties).getToken();

            Room room = roomRepository.save(
                    Room.builder()
                            .member(member)
                            .mode(modeRepository.findByModeSequence(Modes.WORD.getModeSequence()))
                            .title(title)
                            .restrictNum(restrictNum)
                            .currentNum(1)
                            .roomDTTM(now)
                            .roomStatus(false)
                            .build()
            );

            roomMemberRepository.save(
                    RoomMember.builder()
                            .member(member)
                            .room(room)
                            .token(token)
                            .enterDTTM(now)
                            .build()
            );

            this.mapSessions.put(room.getRoomSequence(), session);

            WordGameCreateResponseDto wordGameCreateResponseDto = new WordGameCreateResponseDto(room, token);
            wordGameCreateResponseDto.updateProblemNum(totalQuestion);
            return wordGameCreateResponseDto;

        }catch (OpenViduJavaClientException e1) {
            // If internal error generate an error message and return it to client
            throw new SessionCreateException("세션 만드는데 문제있음");
        }catch (OpenViduHttpException e) {
            //openvidu  관련 Exception
            // If error generate an error message and return it to client
            System.out.println("sessionCreate exception response");
            throw new SessionCreateException("세션 만드는데 문제있음");
        }
    }

    @Override
    public BodyGameCreateResponseDto createBodyGameRoom(String email, BodyGameCreateRequestDto bodyGameCreateRequestDto){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));

        OpenViduRole role = OpenViduRole.PUBLISHER;
        String serverData = "{\"serverData\": \"" + member.getEmail() + "\"}";
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();

        try {
            Date now = new Date();
            String title = bodyGameCreateRequestDto.getTitle();

            int restrictNum = bodyGameCreateRequestDto.getRestrictNum();
            int totalQuestion = bodyGameCreateRequestDto.getTotalQuestion();
            int difficulty = bodyGameCreateRequestDto.getDifficulty();
            int timeLimit = bodyGameCreateRequestDto.getTimeLimit();

            Session session = openVidu.createSession();
            String token = session.createConnection(connectionProperties).getToken();

            Room room = roomRepository.save(
                    Room.builder()
                            .member(member)
                            .mode(modeRepository.findByModeSequence(Modes.BODY.getModeSequence()))
                            .title(title)
                            .restrictNum(restrictNum)
                            .currentNum(1)
                            .roomDTTM(now)
                            .roomStatus(false)
                            .build()
            );

            roomMemberRepository.save(
                    RoomMember.builder()
                            .member(member)
                            .room(room)
                            .token(token)
                            .enterDTTM(now)
                            .build()
            );

            this.mapSessions.put(room.getRoomSequence(), session);

            BodyGameCreateResponseDto bodyGameCreateResponseDto = new BodyGameCreateResponseDto(room, token);
            bodyGameCreateResponseDto.updateSettings(totalQuestion,difficulty,timeLimit);

            return bodyGameCreateResponseDto;

        }catch (OpenViduJavaClientException e1) {
            // If internal error generate an error message and return it to client
            throw new SessionCreateException("세션 만드는데 문제있음");
        }catch (OpenViduHttpException e) {
            //openvidu  관련 Exception
            // If error generate an error message and return it to client
            System.out.println("sessionCreate exception response");
            throw new SessionCreateException("세션 만드는데 문제있음");
        }
    }

    @Override
    public ConversationUpdateResponseDto updateConversationRoom(Long roomSequence, ConversationUpdateRequestDto conversationUpdateRequestDto){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        room.updateRoomTitleAndRestricNum(conversationUpdateRequestDto.getTitle(), conversationUpdateRequestDto.getRestrictNum());

        ConversationUpdateResponseDto conversationUpdateResponseDto = new ConversationUpdateResponseDto(roomRepository.save(room));

        return conversationUpdateResponseDto;

    }

    @Override
    public WordGameUpdateResponseDto updateWordGameRoom(Long roomSequence, WordGameUpdateRequestDto wordGameUpdateRequestDto){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        room.updateRoomTitleAndRestricNum(wordGameUpdateRequestDto.getTitle(), wordGameUpdateRequestDto.getRestrictNum());

        int problemNum = wordGameUpdateRequestDto.getProblemNum();

        WordGameUpdateResponseDto wordGameUpdateResponseDto = new WordGameUpdateResponseDto(roomRepository.save(room));
        wordGameUpdateResponseDto.updateProblemNum(problemNum);

        return wordGameUpdateResponseDto;
    }

    @Override
    public BodyGameUpdateResponseDto updateBodyGameRoom(Long roomSequence, BodyGameUpdateRequestDto bodyGameUpdateRequestDto){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        room.updateRoomTitleAndRestricNum(bodyGameUpdateRequestDto.getTitle(), bodyGameUpdateRequestDto.getRestrictNum());

        int problemNum = bodyGameUpdateRequestDto.getProblemNum();
        int difficulty = bodyGameUpdateRequestDto.getDifficulty();
        int timeLimit = bodyGameUpdateRequestDto.getTimeLimit();

        BodyGameUpdateResponseDto bodyGameUpdateResponseDto = new BodyGameUpdateResponseDto(roomRepository.save(room));
        bodyGameUpdateResponseDto.updateSettings(problemNum,difficulty,timeLimit);

        return bodyGameUpdateResponseDto;
    }

    @Override
    public void leaveRoom(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        RoomMember leaveMember = roomMemberRepository.findByMember(member);
        if(leaveMember==null){
            System.out.println("Problems in getting exiting member");
            return;
        }
        Room leaveRoom = leaveMember.getRoom();
        if(leaveRoom==null){
            System.out.println("Problems in the app server: the SESSION does not exist");

            return;
        }

        roomMemberRepository.delete(leaveMember);
        leaveRoom.updateCurrentNum(leaveRoom.getCurrentNum()-1);
        roomRepository.save(leaveRoom);

        if(email.equals(leaveRoom.getMember().getEmail())){
            List<RoomMember> nextModerator= roomMemberRepository.findRoomMembersByRoomOrderByEnterDTTMAsc(leaveRoom);
            if(nextModerator.size()!=0){
                leaveRoom.updateMemberSeq(nextModerator.get(0).getMember());
                roomRepository.save(leaveRoom);
                return;
            }
            roomRepository.delete(leaveRoom);
            mapSessions.remove(leaveRoom.getRoomSequence());
        }
    }

}