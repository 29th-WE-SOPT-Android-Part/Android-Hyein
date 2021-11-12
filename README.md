# SOPT - Android Assignment

## Week1



##### 1. 시연영상



<img src="https://user-images.githubusercontent.com/68214704/136605271-8330feed-c4e5-4478-b785-ab7166b89806.gif" width="30%" height="30%"/>



***



##### 2.  코드(로직) 설명

- **SininlnActivity.kt**

  ``````kotlin
  lateinit var binding: ActivitySiginlnBinding
  
  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
  
      binding = ActivitySiginlnBinding.inflate(layoutInflater)
  
      loginClick()
      signClick()
      setContentView(binding.root)
  }
  
  private fun loginClick(){
      val intent = Intent(this, HomeActivity::class.java)
      binding.btnLogin.setOnClickListener {
          val id = binding.etId.text.toString()
          val password = binding.etPass.text.toString()
          if(id.isNotEmpty() && password.isNotEmpty()) {
              Toast.makeText(this, "${id}님 환영합니다", Toast.LENGTH_SHORT).show()
              startActivity(intent)
          } else { Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()}
      }
  }
  
  private fun signClick(){
      val intent = Intent(this, SignUpActivity::class.java)
      binding.btnSign.setOnClickListener { startActivity(intent) }
  }
  ``````

  - `binding`

    - 바인딩 변수를 통해 view를 참조

      :key: **setContentView()**

      - 레이아웃 xml의 내용을 파싱하여 뷰를 생성하고, 뷰에 정의된 속성을 설정

        :arrow_forward: xml을 객체화

  - `loginClick()` 

    : 로그인 버튼 클릭 시 실행되는 함수

    - id와 password의 값이 비어있지 않은 경우 `startActivity()` 실행
    - 아닌경우 실패 Toast message 띄우기

  - `signClick() ` 

    : 회원가입 버튼 클릭 시 실행되는 함수

    - `intent`를 통해서 activity 전환



- **SignUpActivity.kt**

  ``````kotlin
  private fun signCompleteClick(){
      binding.btnLogin.setOnClickListener {
          val name = binding.etName.text.toString()
          val id = binding.etId.text.toString()
          val password = binding.etPass.text.toString()
          if(name.isNotEmpty() && id.isNotEmpty() && password.isNotEmpty()) { finish() }
          else { Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()}
      }
  }
  ``````

  - `signCompleteClick()`

    : 회원가입 완료 버튼 클릭 시 실행

    - 3개의 edit textview의 값이 비어있지 않을 때 회원가입 완료 되도록 조건문 사용
    - 종료는 `finish()` 함수 사용



- **border.xml**

  ``````kotlin
  <item>
      <shape android:shape="rectangle">
          <stroke android:width = "2dp" android:color = "@color/pink"/>
          <corners
         		android:radius="10dp"/>
      </shape>
  </item>
  ``````

  - `radius`를 통해 둥근 정도를 조절 (낮을 수록 각지다)
  - 둥근 테두리의 EditText, Button을 위해 만든 아이템



- **strings.xml**

  ``````kotlin
  <resources>
      <string name="app_name">androidAssingment</string>
      <string name="pass_hint">비밀번호를 입력해주세요</string>
      <string name="id_hint">아이디를 입력해주세요</string>
      <string name="pass_name">비밀번호</string>
      <string name="id_name">아이디</string>
      <string name="login_page">로그인</string>
      <string name="sopt_title">SOPTHub</string>
      <string name="login_name">로그인</string>
      <string name="sign_name">회원가입</string>
      <string name="name_name">이름</string>
      <string name="name_hint">이름을 입력해주세요</string>
      <string name="sign_complete">회원가입 완료</string>
      <string name="img_description">hyein\'s picture</string>
      <string name="introduce_name">이름 김혜인</string>
      <string name="introduce_age">나이 22</string>
      <string name="introduce_mbti">MBTI INTP</string>
      <string name="introduce_myself">안녕하세요 김해인 아니고\n부산인인 김혜인입니다!</string>
  </resources>
  ``````

  - 추후 번역 등을 쉽게 해주기 위해 **Extract String resource**를 해주었다.



- **activity_siginln.xml**

  ``````kotlin
  <EditText
      android:id="@+id/et_pass"
      android:layout_width="0dp"
      android:layout_height="50dp"
      android:layout_marginTop="10dp"
      android:layout_marginEnd="30dp"
      android:paddingStart="20dp"
      android:paddingEnd="20dp"
      android:textSize="15sp"
      android:ems="10"
      android:hint="@string/pass_hint"
      android:background="@drawable/border"
      android:inputType="textPassword"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintHorizontal_bias="0.0"
      app:layout_constraintStart_toStartOf="@+id/tv_pass"
      app:layout_constraintTop_toBottomOf="@+id/tv_pass"
      android:autofillHints="no" />
  ``````

  - `hint` 를 사용하여 무엇을 입력해야할 지 알려준다.
  - `inputType`에 `textPassword`를 넣어, 비밀번호를 입력할 때 보이지 않게(*) 한다.
  - `background`를 만들어 둔 `border`파일로 설정하여 커스터마이징 한다.



***



##### 3. 배운 내용, 성장한 내용

1. 자동완성을 위한 힌트 제공 속성 : `autofillHints`

   - 존재의의

     : 자동 완성 서비스에서 앱의 폼 팩터를 올바르게 식별하도록 하려면 자동 완성 힌트를 제공해야 한다.

   - ex) `android:autofillHints="password"`

   

2. viewBinding

   - `build.gradle module`

   ``````kotlin
   viewBinding{
       enabled = true
   }
   ``````

   - 위의 코드를 넣지 않아서 binding 코드가 빨간줄이 떴었다. 이젠 까먹지 말자!

   

3. null 처리

   ``````kotlin
   val id = binding.etId.text?.toString()
   val password = binding.etPass.text?.toString()
   ``````

   - 이 경우 id와 password에 대해 let함수를 통해 null을 처리해주려고 했는데 불가능 했다

     :arrow_forward: 비어있으면 null이 반환되지 않고, ""로 반환된다.

   - 즉 `isEmpty()`나 `isNotEmpty()` 함수로 처리해주면 된다.



***



##### 4. 기타

- xml 편집 시 커스터마이징이 안되는 문제가 생겼다.

  ``````kotlin
  <style name="Theme.AndroidAssingment" parent="Theme.AppCompat.Light">
  ``````

  :ok_hand: themes.xml 의 parent의 값을 바꿔준다.

  

- 버튼의 그림자를 없애고 싶었다.

  ``````kotlin
  android:outlineProvider="none"
  ``````

  :ok_hand: 외각선, none으로 설정해준다.
