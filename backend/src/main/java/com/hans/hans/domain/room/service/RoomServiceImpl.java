package com.hans.hans.domain.room.service;

import com.hans.hans.domain.bodygame.dto.BodyGameCreateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameCreateResponseDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationUpdateRequestDto;
import com.hans.hans.domain.mode.entity.Mode;
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
import com.hans.hans.global.exception.NoExistMemberException;
import com.hans.hans.global.util.ModeName;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{
    // OpenVidu object as entrypoint of the SDK
    private String OPENVIDU_URL;
    // Secret shared with our OpenVidu server
    private String SECRET;
    private OpenVidu openVidu;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final ModeRepository modeRepository;
    private final RoomMemberRepository roomMemberRepository;



    public RoomServiceImpl(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl, RoomRepository roomRepository,MemberRepository memberRepository ,ModeRepository modeRepository, RoomMemberRepository roomMemberRepository) {

        this.SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);

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
    public boolean existRoomByRoomSeq(Long roomSequence){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        if(room==null) return false;
        return true;
    }

    @Override
    public RoomMemberResponseDto enterRoom(String email, Long roomSequence){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        Room room = roomRepository.findByRoomSequence(roomSequence);

        OpenViduRole role = OpenViduRole.PUBLISHER;
        String serverData = "{\"serverData\": \"" + member.getEmail() + "\"}";
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();


        Date now = new Date();

        room.updateCurrentNum(room.getCurrentNum()+1);
        roomRepository.save(room);

        RoomMember roomMember = roomMemberRepository.save(
                RoomMember.builder()
                        .member(member)
                        .room(room)
                        .enterDTTM(now)
                        .build()
        );

        RoomMemberResponseDto roomMemberResponseDto = new RoomMemberResponseDto(roomMember);
        return roomMemberResponseDto;
    }

    @Override
    public RoomsResponseDto searchRoomByTitle(String title, Pageable pageable){
        Page<Room> rooms = roomRepository.findRoomsByTitleContaining(title, pageable);
        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);
        return roomsResponseDto;
    }

    @Override
    public RoomsResponseDto searchRoomByNickname(String nickname, Pageable pageable){
        Member member = memberRepository.findByNickname(nickname);
        Page<Room> rooms = roomRepository.findRoomsByMember(member,pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);
        return roomsResponseDto;
    }

    @Override
    public ConversationCreateResponseDto createConversationRoom(String email, ConversationCreateRequestDto conversationCreateRequestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        Mode mode = modeRepository.findByModeName(ModeName.CONVERSATION.name());

        OpenViduRole role = OpenViduRole.PUBLISHER;
        String serverData = "{\"serverData\": \"" + member.getEmail() + "\"}";
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();
        Room findRoom = roomRepository.findRoomByTitleAndMode(conversationCreateRequestDto.getTitle(),mode);
        if(findRoom!=null){
            return null;
        }
        try {

            Date now = new Date();
            String title = conversationCreateRequestDto.getTitle();
            int restrictNum = conversationCreateRequestDto.getRestrictNum();

            Session session = openVidu.createSession();
            String token = session.createConnection(connectionProperties).getToken();

            Room room = roomRepository.save(
                    Room.builder()
                            .member(member)
                            .mode(modeRepository.findByModeSequence(1L))
                            .title(title)
                            .restrictNum(restrictNum)
                            .currentNum(1)
                            .roomDTTM(now)
                            .roomStatus(false)
                            .token(token)
                            .build());
            roomMemberRepository.save(
                    RoomMember.builder()
                            .member(member)
                            .room(room)
                            .enterDTTM(now)
                            .build()
            );

            ConversationCreateResponseDto conversationCreateResponseDto = new ConversationCreateResponseDto(room);

            return conversationCreateResponseDto;

        } catch (Exception e) {
            // If error generate an error message and return it to client
            System.out.println("sessioncontorller exception response");
            return null;
        }
    }

    @Override
    public WordGameCreateResponseDto createWordGameRoom(String email, WordGameCreateRequestDto wordGameCreateRequestDto){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));

        Date now = new Date();
        String title = wordGameCreateRequestDto.getTitle();

        int restrictNum = wordGameCreateRequestDto.getRestrictNum();
        int problemNum = wordGameCreateRequestDto.getProblemNum();

        Room room = roomRepository.save(
                Room.builder()
                        .member(member)
                        .mode(modeRepository.findByModeSequence(2L))
                        .title(title)
                        .restrictNum(restrictNum)
                        .currentNum(1)
                        .roomDTTM(now)
                        .roomStatus(false)
                        .build());

        roomMemberRepository.save(
                RoomMember.builder()
                        .member(member)
                        .room(room)
                        .enterDTTM(now)
                        .build()
        );

        WordGameCreateResponseDto wordGameCreateResponseDto = new WordGameCreateResponseDto(room);
        wordGameCreateResponseDto.updateProblemNum(problemNum);

        return wordGameCreateResponseDto;
    }

    @Override
    public BodyGameCreateResponseDto createBodyGameRoom(String email, BodyGameCreateRequestDto bodyGameCreateRequestDto){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));

        Date now = new Date();
        String title = bodyGameCreateRequestDto.getTitle();

        int restrictNum = bodyGameCreateRequestDto.getRestrictNum();
        int problemNum = bodyGameCreateRequestDto.getProblemNum();
        int difficulty = bodyGameCreateRequestDto.getDifficulty();
        int timeLimit = bodyGameCreateRequestDto.getTimeLimit();


        Room room = roomRepository.save(
                Room.builder()
                        .member(member)
                        .mode(modeRepository.findByModeSequence(3L))
                        .title(title)
                        .restrictNum(restrictNum)
                        .currentNum(1)
                        .roomDTTM(now)
                        .roomStatus(false)
                        .build());

        roomMemberRepository.save(
                RoomMember.builder()
                        .member(member)
                        .room(room)
                        .enterDTTM(now)
                        .build()
        );

        BodyGameCreateResponseDto bodyGameCreateResponseDto = new BodyGameCreateResponseDto(room);
        bodyGameCreateResponseDto.updateSettings(problemNum,difficulty,timeLimit);

        return bodyGameCreateResponseDto;
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
    public void leaveRoom(Long roomSequence, String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        Room room = roomRepository.findByRoomSequence(roomSequence);

        RoomMember leaveMember = roomMemberRepository.findByMember(member);
        roomMemberRepository.delete(leaveMember);

        if(email.equals(room.getMember().getEmail())){
            List<RoomMember> nextModerator= roomMemberRepository.findRoomMembersByRoomOrderByEnterDTTMAsc(room);
            if(nextModerator.size()!=0){
                room.updateMemberSeq(nextModerator.get(0).getMember());
                roomRepository.save(room);
                return;
            }
            roomRepository.delete(room);
        }
    }

}
