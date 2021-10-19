# SOPT - Android Assignment

## Week2



##### 1. 시연영상



<img src="https://user-images.githubusercontent.com/68214704/137966494-544eb7fa-5386-427a-8eee-cd8db2c29130.gif" width="30%" height="30%"/>



***



##### 2.  코드(로직) 설명

- **SininlnActivity.kt**

  ``````kotlin
  private fun initTransactionEvent(){
      val followerFragment = FollowerFragment()
      val repositoryFragment = RepositoryFragment()
  
      supportFragmentManager.beginTransaction().add(R.id.container_home, followerFragment).commit()
      binding.btnFollower.setOnClickListener{
          if(position == REPOSITORY_POSITION) {
              val transaction = supportFragmentManager.beginTransaction()
              transaction.replace(R.id.container_home, followerFragment)
              position = FOLLOWER_POSITION
              transaction.commit()
          }
      }
      binding.btnRepository.setOnClickListener{
          if(position == FOLLOWER_POSITION) {
              val transaction = supportFragmentManager.beginTransaction()
              transaction.replace(R.id.container_home, repositoryFragment)
              position = REPOSITORY_POSITION
              transaction.commit()
          }
      }
      
      companion object{
          const val FOLLOWER_POSITION = 1
          const val REPOSITORY_POSITION = 2
      }
  }
  ``````

  - `supportFragmentManger`

    - supportFragmentMager로 FragmentManager 호출

      - `FragmentManager`

        : 앱 프래그먼트에서 작업을 추가, 삭제 또는 교체하고 백 스택에 추가하는 등의 작업을 실행하는 클래스

  - `beginTransaction()`

    - 트랜잭션 작업(추가/교체/삭제) 생성하는 기능

    :key: **작업마다 호출해줘야 하는 함수**

  - `replace()`

    :이전 프래그먼트를 제거 후 추가

  - `commit()`

    - 화면에 반영시킨다

  - `companion object`

    - 어떤 프래그먼트 상태인지를 상수로 나타내어 관리

    

- **activity_home.xml**

  ``````kotlin
  <androidx.fragment.app.FragmentContainerView
      android:id="@+id/container_home"
      android:layout_width="match_parent"
      android:layout_height="0dp"
      android:layout_marginTop="10dp"
      app:layout_constraintBottom_toBottomOf="parent"
      app:layout_constraintTop_toBottomOf="@+id/btn_repository" />
  ``````
  
  - FragmentContainerView 사용 권장
  
  

- **FollowerAdapter.kt**

  ``````kotlin
  class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
      val followerList = mutableListOf<FollowerData>()
  
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
          val binding = ItemFollowerListBinding.inflate(
              LayoutInflater.from(parent.context),
              parent, false
          )
          return FollowerViewHolder(binding)
      }
  
      override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
          holder.onBind(followerList[position])
      }
  
      override fun getItemCount(): Int = followerList.size
  
      class FollowerViewHolder(private val binding: ItemFollowerListBinding):
          RecyclerView.ViewHolder(binding.root) {
          fun onBind(data: FollowerData){
              binding.tvName.text = data.name
              binding.tvIntroduce.text = data.introduction
          }
      }
  }
  ``````

  - `Adapter`의  함수

    - onCreateViewHolder()

      : viewHolder 생성 (ItemLayout)

    - onBindViewHolder()

      : 재활용 시 사용됨

    - getItemCount()

  - `ViewHolder` 클래스

    - FollowerViewHolder

      : adapter로 전달받은 data를 onBind함수를 통해 붙여준다.



- **FollowerFragment**

  ``````kotlin
  private var _binding: FragmentFollowerBinding? = null
  private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
  private var _followerAdapter: FollowerAdapter? = null
  private val followerAdapter get() = _followerAdapter ?: error("followerAdapter이 초기화 되지 않았습니다.")
  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
      _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
  
      initAdapter()
  
      return binding.root
  }
  
  override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
      _followerAdapter = null
  }
  
  private fun initAdapter(){
      _followerAdapter = FollowerAdapter()
      binding.rvFollower.adapter = followerAdapter
  
      followerAdapter.followerList.addAll(
          listOf(
              FollowerData("문다빈", "안드로이드 파트장"),
              FollowerData("김현아", "기획 파트장"),
              FollowerData("이성현", "디자인 파트장"),
              FollowerData("장혜령", "iOS 파트장"),
              FollowerData("김우영", "서버 파트장"),
              FollowerData("김의진", "웹 파트장")
          )
      )
  
      followerAdapter.notifyDataSetChanged()
  }
  ``````

  - 메모리 누수 방지
    - binding, followerAdapter같은 경우 좀비객체가 되는 것을 방지하기 위해 onDestroyView에서 객체참조를 해제



- **fragment_repository.xml**

  ``````kotlin
  <androidx.recyclerview.widget.RecyclerView
      android:id="@+id/rv_follower"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
      app:spanCount="2"
      tools:itemCount="4"
      tools:listitem="@layout/item_repository_list"/>
  ``````

  - `LayoutManager`
    - item의 배치 규칙을 관리하는 클래스
      - Grid를 이용하여 격자식으로 보이도록 관리



- **item_repository_list.xml**

  ``````kotlin
  android:ems="10"
  android:maxLines="1"
  android:ellipsize="end"
  ``````

  - ems 크기 10 이상 넘어갈 때, maxLines가 1이므로 한 줄을 넘기지 못하고 ellipsize로 ...을 나타내어 요약하게 된다.

  

***



##### 3. 배운 내용, 성장한 내용

1. transaction 작업 단위 호출

   ``````kotlin
   val transaction = supportFragmentManager.beginTransaction()
   ``````

   - 위 코드를 작업 단위로 호출해주어야 한다.

     :arrow_forward: 그렇지 않으면 제대로 반영이 되지않는다

   

2. maxLenght

   ``````kotlin
   android:maxLength="10"
   android:ellipsize="end"
   ``````

   - 위와 같은 식으로 하면 ellipsize가 제대로 적용되지 않았다.

   ``````kotlin
   android:ems="10"
   android:maxLines="1"
   android:ellipsize="end"
   ``````

   - maxLines를 사용했을 땐 ellipsize가 적용이 되었으므로 이것과 ems를 이용하여 적용했다.

   

3. spanCount

   ``````kotlin
   app:spanCount="2"
   ``````

   - Grid로 나타낼 때 열을 지정하는 값이다.

