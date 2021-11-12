# SOPT - Android Assignment

## Week4



##### 1. 시연영상

- app 영상

<img src="https://user-images.githubusercontent.com/68214704/141417154-694f7412-d83a-480c-8e71-3543ea0d967b.gif" width="30%" height="30%"/>

- postman

  - 로그인

  <img src="https://user-images.githubusercontent.com/68214704/141421492-18aebcff-6cad-4992-b85a-37a56fc4ac1a.JPG">

  

  - 회원가입

    <img src="https://user-images.githubusercontent.com/68214704/141421586-f73024b8-7d79-41fd-877d-452c04da73d1.JPG">

    

***



##### 2.  코드(로직) 설명

- **build.grandle**

  ``````kotlin
   // 서버 연결을 위한 Retrofit2
  implementation "com.squareup.retrofit2:retrofit:2.9.0"
  
  // Retrofit2에서 gson 사용을 위한 컨버터
  implementation "com.squareup.retrofit2:converter-gson:2.9.0"
  
  // gson
  implementation "com.google.code.gson:gson:2.8.6"
  ``````

  - **Retrofit2** : 서버와 클라이언트 간 http 통신을 위한 라이브러리
    - JSON 구조의 데이터를 쉽게 가져오고 업로드
    - **JSON** : 프로그래밍 언어 :x: 플랫폼 독립형 데이터 포맷 :o:
  - **gson** : Java에서 Json을 파싱하고, 생성하기 위해 사용되는 구글에서 개발한 오픈소스
    - Retrofit2는 gson을 자바 객체로 변환하는 자체 컨버터를 가지고 있지 않으므로 따로 넣어줘야 한다.

- **AndroidManifest.xml**

  ``````kotlin
  <uses-permission android:name="android.permission.INTERNET"/>
  
  <application
  	android:usesCleartextTraffic="true"
  ``````

  - 인터넷 접속 권한 부여
  - 따로 http 통신을 하기 위해선 `android:usesCleartextTraffic`를 true로 해주어야 한다.

  

- **RequestLoginData**

  ``````kotlin
  import com.google.gson.annotations.SerializedName
  
  data class RequestLoginData(
      @SerializedName("email")
      val id:String,
      val password:String
  )
  ``````

- **RequestSignUpData**

  ``````kotlin
  import com.google.gson.annotations.SerializedName
  
  data class RequestSignUpData(
      @SerializedName("email")
      val id:String,
      val name:String,
      val password:String
  )
  ``````
  
  - **서버 Request 객체**
  - json객체의 키 값과 타입을 데이터 클래스의 변수명과 타입에 일치 시킨다.
  - `SerializedName` : json의 키 값 / 클래스의 변수명 이 다를 경우 맵핑 시켜준다.
    - ex) json의 키 값 : email / 클래스의 변수명 : id
  
  

- **ResponseLoginData**

  ``````kotlin
  data class ResponseLoginData(
      val status: Int,
      val success: Boolean,
      val message: String,
      val data: Data
  ) {
      data class Data(
          val id: Int,
          val name: String,
          val email: String
      )
  }
  ``````

- **ResponseSignUpData**

  ``````kotlin
  data class ResponseSignUpData(
      val status: Int,
      val success: Boolean,
      val message: String,
      val data: Data
  ) {
      data class Data(
          val id: Int,
          val name: String,
          val email: String
      )
  }
  ``````

  - **서버 Response 객체**

  - json객체의 키 값과 타입을 데이터 클래스의 변수명과 타입에 일치 시킨다.

  - 중첩 클래스 구조

    - Data 클래스를 중첩 클래스 구조로 만들어 주었다.

    

- **LoginService**

  ``````kotlin
  interface LoginService {
      @Headers("Content-Type: application/json")
      @POST("user/login")
      fun postLogin(
          @Body requestLoginData: RequestLoginData
      ) : Call<ResponseLoginData>
  }
  ``````


- **SignUpService**

  ``````kotlin
  interface SignUpService {
      @Headers("Content-Type: application/json")
      @POST("user/signup")
      fun postSignUp(
          @Body requestSingUpData: RequestSignUpData
      ) : Call<ResponseSignUpData>
  }
  ``````

  - **Retrofit Interface**

    - 서버에 어떠한 요청을 보내면 어떻게 온다는 **일종의 상호작용 방법을 정의**하는 부분

  - `@Headers` : @Header 어노테이션 이용하여 헤더값을 넣어준다.

  - `@POST` : HTTP 메소드(POST, GET, ..) 정의 후 해당 API의 url을 작성

  - `postSignUp`함수

    - `@Body` : RequestBody 데이터를 넣어 준다. (실행시켰을 때 넘길 데이터)

    - `Call<Type>` : 동기적 또는 비동기적으로 Type을 받아오는 객체

      - ResponseLoginData/ResponseSignUpData 을 받아온다.

      

- **ServiceCreator**

  ``````kotlin
  object ServiceCreator {
      private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
  
      private val retrofit : Retrofit = Retrofit
          .Builder() // 레트로핏 빌더 생성(생성자 호출)
          .baseUrl(BASE_URL) // 빌더 객체의 baseUrl호출
          .addConverterFactory(GsonConverterFactory.create()) // gson컨버터 연동
          .build() // Retrofit 객체 반환
  
      // 인터페이스 객체를 create에 넘겨 실제 구현체 생성
      val loginService: LoginService = retrofit.create(LoginService::class.java)
      val singUpService : SignUpService = retrofit.create(SignUpService::class.java)
  }
  ``````

  - **Retrofit Interface 실제 구현체**
    
    - Retrofit Interface를 실질적으로 구현하여 생성해주는 객체
    - 정의한 상호작용 방법을 실제로 구현하는 부분
    
  - `retrofit` : Retrofit 객체 생성

  - `loginService`, `signUpService` : 실제 구현체

  - **Object**

    - 싱글톤

      : 최초 한번만 메모리를 할당하고(Static) 그 메모리에 인스턴스를 만들어 사용하는 디자인 패턴

      - 서버 호출이 필요할 때마다 Retrofit 객체를 만드는 것이 아니라 *한 번*만 생성




- **SinginActivity**

  ``````kotlin
  private fun loginClick(){
      binding.btnLogin.setOnClickListener {
          initNetwork()
      }
  }
  
  private fun initNetwork(){
      val requestLoginData = RequestLoginData(
          id = binding.etId.text.toString(),
          password = binding.etPass.text.toString()
      )
  
      val call: Call<ResponseLoginData> = ServiceCreator.loginService.postLogin(requestLoginData)
  
      call.enqueue(object : Callback<ResponseLoginData> {
          override fun onResponse(
              call: Call<ResponseLoginData>,
              response: Response<ResponseLoginData>
          ) {
              if(response.isSuccessful){
                  Toast.makeText(this@SignInActivity, "${response.body()?.data?.name}님 반갑습니다", Toast.LENGTH_SHORT).show()
  
                  startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
              } else
              Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다", Toast.LENGTH_SHORT).show()
  
          }
  
          override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
              Log.e("NetworkTest","error:$t")
          }
      })
  }
  ``````

- **SingUpActivity**

  ``````kotlin
  private fun signCompleteClick(){
      binding.btnLogin.setOnClickListener {
          initNetwork()
      }
  }
  
  private fun initNetwork(){
      val requestSignUpData = RequestSignUpData(
          id = binding.etId.text.toString(),
          name = binding.etName.text.toString(),
          password = binding.etPass.text.toString()
      )
  
      val call: Call<ResponseSignUpData> = ServiceCreator.singUpService.postSignUp(requestSignUpData)
  
      call.enqueue(object : Callback<ResponseSignUpData> {
          override fun onResponse(
              call: Call<ResponseSignUpData>,
              response: Response<ResponseSignUpData>
          ) {
              if(response.isSuccessful){
                  finish()
              } else
              Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
  
          }
  
          override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
              TODO("Not yet implemented")
          }
      })
  }
  ``````

  - `initNetwork()` : 서버와 통신하는 코드

  - `requestLoginData`, `requestSignUpData`

    - 통신할 때 필요한 data
    - 서버에 요청을 보내기 위한 RequestData

  - `call` : interface구현체에 접근하여 Call 객체를 받아온다

  - `call.enqueue` : 서버 통신을 비동기적으로 요청

    - `Callback` 익명 클래스 선언

      :arrow_forward: Call 객체의 비동기 작업 이후 작업이 끝날 때 행동을 Callback 객체로 표현

    - `onResponse`

      - Status Code가 200~300 사이 일 때 reponse.isSuccessful이 true를 반환

    - `onFailur`

      - reponse.isSuccessful가 false이거나 body()에 값이 없을 경우

  - 버튼 클릭 시 `initNetwork()` 호출

  

***



##### 3. 배운 내용, 성장한 내용

1. **Object**

   - 왜 class가 아닌 object를 쓰는가 의문을 가졌는데, object의 기능(싱글톤)을 알게되었다

2. 비동기 처리

   ``````kotlin
   call.enqueue(object : Callback<ResponseSignUpData> {})
   ``````

   - 비동기 처리를 위해 위와 같은 코드를 사용함을 알게 되었다
