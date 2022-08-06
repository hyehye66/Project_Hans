
const Google = {
  async GoogleLoginSuccess(googleUser){
    const req_body = {
      googleAccessToken: googleUser.Zi.access_token,
      name : googleUser.w3.ig,
      id: googleUser.w3.Eea,
      email: googleUser.w3.U3,
      profileIMG: googleUser.w3.paa,
      source: 'g',
    }
    await social_login(req_body)
  },
  GoogleLoginFailure(){
    LoginFailure();
    console.log('fail')
  }
}