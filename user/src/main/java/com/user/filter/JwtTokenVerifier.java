package com.user.filter;

import com.google.common.base.Strings;
import com.user.properties.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {
  private final JwtProperties jwtProperties;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader = httpServletRequest.getHeader("Authorization");

    if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(httpServletRequest, httpServletResponse);
      return;
    }
    try {
      String token = authorizationHeader.replace(jwtProperties.getTokenPrefix() + " ", "");
      String secretKey = jwtProperties.getSecretKey();
      Jws<Claims> claimsJws =
          Jwts.parser()
              .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
              .parseClaimsJws(token);
      Claims body = claimsJws.getBody();
      String username = body.getSubject();
      var authorities = (List<Map<String, String>>) body.get("authorities");
      Set<SimpleGrantedAuthority> authority =
          authorities.stream()
              .map(m -> new SimpleGrantedAuthority(m.get("authority")))
              .collect(Collectors.toSet());
      Authentication authentication =
          new UsernamePasswordAuthenticationToken(username, null, authority);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(httpServletRequest, httpServletResponse);
    } catch (JwtException e) {
      throw new IllegalStateException("Token Cannot be trusted");
    }
  }
}
