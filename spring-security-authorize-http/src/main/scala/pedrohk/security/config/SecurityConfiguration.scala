package pedrohk.security.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.{NoOpPasswordEncoder, PasswordEncoder}
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

  @Bean
  def securityFilterChain(
                           httpSecurity: HttpSecurity
                         ): SecurityFilterChain = {

    httpSecurity
      .authorizeHttpRequests(authorize =>
        authorize
          .requestMatchers("/public/**")
          .permitAll()
          .requestMatchers("/portal/**")
          .authenticated()
          .anyRequest()
          .authenticated()
      )
      .httpBasic(customizer => {})

    httpSecurity.build()
  }

  @Bean
  def passwordEncoder(): PasswordEncoder = {
    NoOpPasswordEncoder.getInstance()
  }
}