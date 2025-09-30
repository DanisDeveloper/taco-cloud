package galimullin.danis.tacocloud.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileWriterIntegrationConfig {

//    @Bean
//    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
//    public GenericTransformer<String, String> uppercaseTransformer(){
//        return text -> text.toUpperCase();
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "fileWriterChannel")
//    public FileWritingMessageHandler fileWriter(){
//        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/home/danis/tacocloud"));
//        handler.setExpectReply(false);
//        handler.setFileExistsMode(FileExistsMode.APPEND);
//        handler.setAppendNewLine(true);
//        return handler;
//    }

    @Bean
    public IntegrationFlow fileWritingFlow() {
        return IntegrationFlow
                .from(MessageChannels.direct("textInChannel"))
                .<String, String>transform(text -> text.toUpperCase())
                .channel(MessageChannels.direct("FileWriterChannel"))
                .handle(Files.outboundAdapter(new File("/home/danis/tacocloud"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))
                .get();
    }
}
