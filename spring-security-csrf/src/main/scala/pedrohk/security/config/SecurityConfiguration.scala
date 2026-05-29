package pedrohk.security.config

  import org.springframework.context.annotation.Bean
  import org.springframework.context.annotation.Configuration

  import org.springframework.security.config.annotation.web.builders.HttpSecurity
  import org.springframework.security.web.SecurityFilterChain
  import org.springframework.security.crypto.password.NoOpPasswordEncoder
  import org.springframework.security.crypto.password.PasswordEncoder

  @Configuration
  class SecurityConfiguration {

    @Bean
    def securityFilterChain(
                             httpSecurity: HttpSecurity
                           ): SecurityFilterChain = {

      httpSecurity
        .authorizeHttpRequests(authorize =>
          authorize
            .anyRequest()
            .authenticated()
        )
        .httpBasic(customizer => {})
        .csrf(customizer => {})

      httpSecurity.build()
    }

    @Bean
    def passwordEncoder(): PasswordEncoder = {
      NoOpPasswordEncoder.getInstance()
    }
  }