package kr.pe.karsei.springtracingotel.configs;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class TracingConfig {
    // Ref. https://github.com/openzipkin/b3-propagation
    private static final String HEADER_TRACE_ID = "X-B3-TraceId";

    @Bean
    Filter traceIdInResponseFilter(Tracer tracer) {
        return (request, response, chain) -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                HttpServletResponse resp = (HttpServletResponse) response;
                // 기본적으로 TraceId 는 보안 문제로 Http 응답 Header 에서 보여주지 않음
                // Ref. https://cloud.spring.io/spring-cloud-static/spring-cloud-sleuth/1.2.5.RELEASE/single/spring-cloud-sleuth.html#_example
                // Ref. https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/html/howto.html#how-to-add-headers-to-the-http-server-response
                resp.addHeader(HEADER_TRACE_ID, currentSpan.context().traceId());
            }
            chain.doFilter(request, response);
        };
    }
}
