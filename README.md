# SpringSecurityWidthUdemy

<h2>Section2</h2>
<h3>SecurityConfigurationFilterChain Custom</h3>
  <ul>
    <li>requestMatcher("").authenticated()</li>
    <li>anyRequest.permitAll()</li>
    <li>anyRequest.denayAll()</li>
  </ul>
<h2>Section3</h2>
<h3>InternalFlow About UserLogin </h3>
  <ul>
    <li>
      <h3>InMemoryUserDetailsManager</h3>
      <p>UserDetailsManager는 CRUD와 회원관련 기능등을 추상화 해놓은 인터페이스임.</p>
    </li>
    <li>
      <h3>UserDetails</h3>
      <p>User에 추상메소드가 정의되어 있음.</p>
    </li>
    <li>
      <h3>UserDetailsService</h3>
      <p>UserDetailsService는 loadUserByUsername(String username) 하나만 있는 인터페이스임</p>
      <p>해당 인터페이스를 implements 하여 Bean설정함으로써 SpringSecurity 내에서 user를 가져오는 로직을 커스텀할 수 있음.</p>
    </li>
  </ul>
<h2>Section4</h2>
<h3>PasswordEncoder in SpringSecurity</h3>
<ul>
    <li>
        <h3>PasswordEncoder Interface</h3>
        <p>인코딩 메서드, match(비교) 메서드, 인코딩 업그레이드 메서드가 정의되어 있고, 모든 종류의 PasswordEncdoer 객체들은 해당 인터페이스를 구현하는 구현체임</p>
    </li>
    <li>
        <h3>PasswordEncoder 종류</h3>
        <ul>
            <li> 
                <p style="font-weight: bold; font-size: 17px">IN Legacy or Demo Project</p>
                <p>NoOpPasswordEncoder, StandardPasswordEncoder, Pbkdf2PasswordEncoder</p>
            </li>
            <li> 
                <p style="font-weight: bold; font-size: 17px">IN Product</p>
                <p>BCRyptPasswordEncoder, SCRyptPasswordEncoder, Argon2PasswordEncoder</p>
                <p>
                    위 작성 순서대로 보안은 강력하지만, 그만큼 리소스 소모가 큼. BCrypt방식을 쓰는게 적당함.
                </p>
            </li>
        </ul>
    </li>
</ul>
<h2>Section5</h2>
<h3>AuthenticationProvider Custom</h3>
<ul>
    <li>
        <h3>AuthenticationProvider Interface</h3>
        <p>authenticate 메소드 : username, password 등을 가진 Authentication 객체를 parameter로 받고, return한다.</p>
    </li>
    <li>
        <h3>Custom AuthenticationProvider</h3>
        <p>

        </p>
    </li>
</ul>
