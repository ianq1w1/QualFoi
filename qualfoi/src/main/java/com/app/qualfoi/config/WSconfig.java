@Configuration
@EnableWebSocketMessageBroker
public class WSconfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topico");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/qf-chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}