# SOPT - Android Assignment

## Week3



##### 1. 시연영상



<img src="https://user-images.githubusercontent.com/68214704/137966494-544eb7fa-5386-427a-8eee-cd8db2c29130.gif" width="30%" height="30%"/>



***



##### 2.  코드(로직) 설명

- **HomeActivity.kt**

  ``````kotlin
  private fun initBottomNavigation(){
      val profileFragment = ProfileFragment()
      val homeFragment = HomeFragment()
      val cameraFragment = CameraFragment()
  
      supportFragmentManager.beginTransaction().add(R.id.container_home, profileFragment).commit()
      binding.bnvHome.setOnItemSelectedListener {
          when(it.itemId){
              R.id.menu_home -> {
                  val transaction = supportFragmentManager.beginTransaction()
                  transaction.replace(R.id.container_home, homeFragment).commit()
                  return@setOnItemSelectedListener true
              }
              R.id.menu_person -> {
                  val transaction = supportFragmentManager.beginTransaction()
                  transaction.replace(R.id.container_home, profileFragment).commit()
                  return@setOnItemSelectedListener true
              }
              else->{
                  val transaction = supportFragmentManager.beginTransaction()
                  transaction.replace(R.id.container_home, cameraFragment).commit()
                  return@setOnItemSelectedListener true
              }
          }
      }
  }
  ``````
  
  - `when`을 통해 menu를 관리, `transaction`을 통해 fragment이동
  
    
  
- **ProfileFragment.kt**

  ``````kotlin
  private fun initTransactionEvent(){
      val followerFragment = FollowerFragment()
      val repositoryFragment = RepositoryFragment()
  
      childFragmentManager.beginTransaction().add(R.id.container_profile, followerFragment).commit()
      binding.btnFollower.setOnClickListener{
          if(position == REPOSITORY_POSITION) {
              val transaction = childFragmentManager.beginTransaction()
              transaction.replace(R.id.container_profile, followerFragment).commit()
              position = FOLLOWER_POSITION
          }
      }
      binding.btnRepository.setOnClickListener{
          if(position == FOLLOWER_POSITION) {
              val transaction = childFragmentManager.beginTransaction()
              transaction.replace(R.id.container_profile, repositoryFragment).commit()
              position = REPOSITORY_POSITION
          }
      }
  
  }
  
  private fun initImage(){
      Glide.with(this)
      .load("https://avatars.githubusercontent.com/u/68214704?v=4")
      .circleCrop()
      .into(binding.imgSelf)
  }
  ``````

  - `ChildFragmentManager`로 transaction을 관리
  - `Glide`를 통해 외부의 image를 넣어준다.

  

- **HomeFragment.kt**

  ``````kotlin
  private fun initAdapter(){
      val fragmentList = listOf(HomeFollowingFragment(), HomeFollowerFragment())
  
      homeViewPagerAdapter = HomeTabViewPagerAdapter(this)
      homeViewPagerAdapter.fragments.addAll(fragmentList)
  
      binding.vpHome.adapter = homeViewPagerAdapter
  }
  
  private fun initTabLayout(){
      val tabLabel = listOf("팔로잉", "팔로워")
  
      TabLayoutMediator(binding.tlHome, binding.vpHome) {
          tab, position -> tab.text = tabLabel[position]
      }.attach()
  }
  ``````
  
  - viewPager2 + TabLayout
  
  

- **FollowerAdapter.kt**

  ``````kotlin
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
      val binding = ItemFollowerListBinding.inflate(
          LayoutInflater.from(parent.context),
          parent, false
      )
      Glide.with(parent.context)
      .load("https://avatars.githubusercontent.com/u/68214704?v=4")
      .circleCrop()
      .into(binding.ivProfile)
      return FollowerViewHolder(binding)
  }
  ``````

  - `onCreateViewHolder`안에서 Glide를 사용했다.
    - Glide.with은 매개변수로 activity, fragment, view, context 등을 받는데, adapter안에서 context를 넣어주기 위해 viewGroup을 매개변수로 받아오는 onCreateViewHolder안에서 사용했다.



- **fragment_home.xml**

  ``````kotlin
  <com.google.android.material.tabs.TabLayout
      android:id="@+id/tl_home"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:layout_marginTop="36dp"
      app:tabTextColor="@color/pink"
      app:tabTextAppearance="@style/tab_text"
      app:tabIndicatorColor="@color/pink"
      app:tabIndicatorHeight="3dp"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toBottomOf="@+id/tv_sign">
  
      </com.google.android.material.tabs.TabLayout>
  
      <androidx.viewpager2.widget.ViewPager2
      android:id="@+id/vp_home"
      android:layout_width="match_parent"
      android:layout_height="0dp"
      app:layout_constraintBottom_toBottomOf="parent"
      app:layout_constraintTop_toBottomOf="@+id/tl_home"/>
  ``````

  - `tabTextAppearance`
    - tab의 text들에 style을 적용시켜줄 수 있다.
  - `tabIndicatorColor`
    - tab 밑줄의 색을 지정
  - `tabIndicatorHeight`
    - tab 밑줄의 높이를 지정



- **activity_home.xml**

  ``````kotlin
  <com.google.android.material.bottomnavigation.BottomNavigationView
      android:id="@+id/bnv_home"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:paddingTop="7dp"
      app:menu="@menu/menu_bottom"
      app:itemIconTint="@color/selector_bottom_navi"
      app:itemRippleColor="@color/nav_pink"
      android:background="@color/white"
      app:itemTextColor="@color/selector_bottom_navi"
      app:layout_constraintBottom_toBottomOf="parent"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintStart_toStartOf="parent" />
  ``````
  
  - `BottomNavigation`선언
    - `itemIconTint`로 선택 시 색이 바뀌도록 지정하였다.
  
  

***



##### 3. 배운 내용, 성장한 내용

1. childFragmentManager

   ``````kotlin
   childFragmentManager.beginTransaction()
   ``````

   - fragment에서 fragment에 대한 transaction을 진행할 때는 `supportFragmentManager`를 사용할 수 없다.

     :arrow_forward: 대신 `childFragmentManager`사용

   

2. checked

   ``````kotlin
   <item android:drawable="@drawable/rectangle_border_pink" android:state_focused="true"/>
   <item android:drawable="@drawable/rectangle_fill_gray" android:state_focused="false"/>
   ``````

   - 처음에 bottom navigation 메뉴들을 저런식으로 state_focused로 해서 되지 않았었다.

   ``````kotlin
   <item android:color="@color/nav_pink" android:state_checked="true" />
   <item android:color="@color/nav_gray" android:state_checked="false"/>
   ``````
   
   - 이런 식으로 checked를 이용해주어야 한다.
   
