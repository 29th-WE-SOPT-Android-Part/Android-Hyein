# SOPT - Android Assignment

## Week7



##### 1. 시연영상

<img src="https://user-images.githubusercontent.com/68214704/146550590-594e576d-370c-41f5-8641-38da371f62f1.gif" width="30%" height="30%"/>



***



##### 2.  코드(로직) 설명

- **build.grandle**

  ``````kotlin
  // Navigation
  implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
  implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
  ``````
  
  - navigation component 사용을 위해 추가해준다.
  
  
  
- **nav_onboarding.xml**

  ``````kotlin
  <?xml version="1.0" encoding="utf-8"?>
  <navigation xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:id="@+id/nav_onboarding"
      app:startDestination="@id/boardingFragment1">
  
      <fragment
          android:id="@+id/boardingFragment1"
          android:name="org.sopt.androidassingment.ui.onboarding.BoardingFragment1"
          android:label="fragment_boarding1"
          tools:layout="@layout/fragment_boarding1" >
          <action
              android:id="@+id/action_boardingFragment1_to_boardingFragment2"
              app:destination="@id/boardingFragment2" />
      </fragment>
      <fragment
          android:id="@+id/boardingFragment3"
          android:name="org.sopt.androidassingment.ui.onboarding.BoardingFragment3"
          android:label="fragment_boarding3"
          tools:layout="@layout/fragment_boarding3" >
          <action
              android:id="@+id/action_boardingFragment3_to_signInActivity"
              app:destination="@id/signInActivity" />
      </fragment>
      <fragment
          android:id="@+id/boardingFragment2"
          android:name="org.sopt.androidassingment.ui.onboarding.BoardingFragment2"
          android:label="fragment_boarding2"
          tools:layout="@layout/fragment_boarding2" >
          <action
              android:id="@+id/action_boardingFragment2_to_boardingFragment3"
              app:destination="@id/boardingFragment3" />
      </fragment>
      <activity
          android:id="@+id/signInActivity"
          android:name="org.sopt.androidassingment.SignInActivity"
          android:label="activity_sign_in"
          tools:layout="@layout/activity_sign_in" />
  </navigation>
  ``````

  - onboarding시 fragment간의 이동을 효율적으로 하기 위해 만든 navigation 파일

  

- **nav_home.xml**

  ``````kotlin
  <?xml version="1.0" encoding="utf-8"?>
  <navigation xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:id="@+id/nav_home"
      app:startDestination="@id/profileFragment">
  
      <fragment
          android:id="@+id/profileFragment"
          android:name="org.sopt.androidassingment.ui.profile.ProfileFragment"
          android:label="fragment_profile"
          tools:layout="@layout/fragment_profile" >
          <action
              android:id="@+id/action_profileFragment_to_settingActivity"
              app:destination="@id/settingActivity" />
      </fragment>
      <activity
          android:id="@+id/settingActivity"
          android:name="org.sopt.androidassingment.SettingActivity"
          android:label="activity_setting"
          tools:layout="@layout/activity_setting" />
  </navigation>
  ``````

  - profile fragment에서 setting activity(자동로그인 해제하는 페이지)로 가기 위해 만든 navigation



- **BoardingFragment1, 2**

  ``````kotlin
  private fun clickBtn(){
      binding.btnNext1.setOnClickListener{
          findNavController().navigate(R.id.action_boardingFragment1_to_boardingFragment2)
      }
  }
  ``````
  
  - action을 설정해주어 버튼 클릭 시 fragment가 전환되게 한다.
  
- **BoardingFragment3**

  ``````kotlin
  private fun startClick(){
      binding.btnStart.setOnClickListener{
          findNavController().navigate(R.id.action_boardingFragment3_to_signInActivity)
          requireActivity().finish()
      }
  }
  ``````
  
  - 마지막 온보딩 프래그먼트에서 button 클릭 시 로그인 activity로 전환되게 끔 한다.
  - 호스트 activity는 `requireActivity().finish()` 를 이용하여 종료
  
  
  
- **SignInActivity**

  ``````kotlin
  private fun initClickEvent(){
      binding.ibCheck.setOnClickListener{
          binding.ibCheck.isSelected = !binding.ibCheck.isSelected
  
          SOPTSharedpreferences.setAutoLogin(this, binding.ibCheck.isSelected)
      }
  }
  
  private fun isAutoLogin(){
      if(SOPTSharedpreferences.getAutoLogin(this)){
          shortToast("자동로그인 되었습니다")
          startActivity(Intent(this, HomeActivity::class.java))
          finish()
      }
  }
  ``````

  - 자동 로그인 버튼 클릭 시 자동로그인이 설정되게 끔 한 후(`setAutoLogin`), 설정이 되어있다면(`getAutoLogin`) 자동로그인이 되게끔 만들었다.(activity 전환)

- **SettingActivity**

  ``````kotlin
  private fun initClickEvent(){
      binding.tvAutoCancle.setOnClickListener{
          shortToast("자동 로그인 해제")
          SOPTSharedpreferences.removeAutoLogin(this)
          SOPTSharedpreferences.clearStorage(this)
      }
  }
  ``````
  
  - 버튼 클릭 시 `removeAutoLogin`과 `clearStorage`를 통해 자동 로그인이 해제되게끔 만들었다.


- **SOPTSharedpreferences**

  ``````kotlin
  object SOPTSharedpreferences {
      private const val STORAGE_KEY="USER_AUTH"
      private const val AUTO_LOGIN = "AUTO_LOGIN"
  
      fun getAutoLogin(context: SignInActivity): Boolean{
          val preferences = getPreference(context)
          return preferences.getBoolean(AUTO_LOGIN, false)
      }
  
      fun setAutoLogin(context: SignInActivity, value: Boolean){
          val preferences = getPreference(context)
          preferences.edit()
              .putBoolean(AUTO_LOGIN, value)
              .apply()
      }
  
      fun removeAutoLogin(context: Context){
          val preferences = getPreference(context)
          preferences.edit()
              .remove(AUTO_LOGIN)
              .apply()
      }
  
      fun clearStorage(context: Context){
          val preferences = getPreference(context)
          preferences.edit()
              .clear()
              .apply()
      }
  
      private fun getPreference(context: Context): SharedPreferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
  }
  ``````

  - 중복되는 코드는 함수(`getPreference`)로 만들어서 바로 변수에 대입해주었다.

    

- **activity_on_boarding.xml**

  ``````kotlin
  <androidx.fragment.app.FragmentContainerView
      android:id="@+id/container_start"
      android:name="androidx.navigation.fragment.NavHostFragment"
      android:layout_width="match_parent"
      android:layout_height="0dp"
      app:defaultNavHost="true"
      app:layout_constraintBottom_toBottomOf="parent"
      app:layout_constraintTop_toBottomOf="@+id/cl_top"
      app:navGraph="@navigation/nav_onboarding"/>
  ``````
  
  - navigation을 쓰는 경우, host activity에 위와 같은 코드를 넣어 설정해주어야 한다.
    



***



##### 3. Util 클래스 코드 및 패키징 방식

- 최상단

  - data / ui / util 세가지로 분류했습니다.

  <img src="https://user-images.githubusercontent.com/68214704/146551837-03f9d8b0-1fe2-4ad7-bab3-e2d1c390d975.png" align="left">



- data package

  <img src="https://user-images.githubusercontent.com/68214704/146552130-efa64041-d222-4c8c-a7ce-3f44f92c9f7a.png" align="left">

  

  - request / response / server package

    <img src="https://user-images.githubusercontent.com/68214704/146552382-83837704-b228-4424-a334-81a7392927de.png" align="left">



- ui package

  <img src="https://user-images.githubusercontent.com/68214704/146552496-a4d74b98-f860-48ae-8539-f16a8a07b787.png" align="left">

  

  - camera / home / onboarding / profile

    <img src="https://user-images.githubusercontent.com/68214704/146552625-3a505d03-1b42-4aff-af91-b09049dafa76.png" align="left">

    



:arrow_forward: data package : 기능별 분류

:arrow_forward: ui package : view 별 분류



- util class

  util class는 아직 뭘 만들어야할지 몰라서 작성하지 않았고, 확장함수 하나만 작성하였다

  ``````kotlin
  fun Context.shortToast(message: String){
      Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }
  ``````

  - toast 함수



***



##### 4. 배운 내용, 성장한 내용

- `requirActivity`

  ``````kotlin
  requireActivity().finish()
  
  requireActivity().shortToast("")
  ``````

  - fragment에서 activity에서 쓸 수 있을 것 같은? 함수들을 사용할 때는 requireActivity를 이용하여 호출하면 된 다는 것을 알게 되었다.



- gradle ..

  - warning 표시가 되어있던 gradle의 implement 구문들을 alt+enter로 다 업데이트 하였는데 그 이후 이상한 에러들이 나타나서 고치기가 힘들었다.

    주의해서 고쳐야 할 듯 하다..
