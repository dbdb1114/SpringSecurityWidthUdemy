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
      <p>User에 대한 몇 가지 추상메소드가 정의되어 있음.</p>
    </li>
    <li>
      <h3>UserDetailsService</h3>
      <p>UserDetailsService는 loadUserByUsername(String username) 하나만 있는 인터페이스임</p>
      <p>해당 인터페이스를 implements 하여 Bean설정함으로써 SpringSecurity 내에서 user를 가져오는 로직을 커스텀할 수 있음.</p>
    </li>
  </ul>

