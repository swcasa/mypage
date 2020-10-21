package carRent;

import carRent.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SystemViewHandler {


    @Autowired
    private SystemRepository systemRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                System system = new System();
                // view 객체에 이벤트의 Value 를 set 함
                system.setId(ordered.getId());
                system.setCarId(ordered.getCarId());
                system.setQty(ordered.getQty());
                system.setStatus(ordered.getStatus());
                // view 레파지 토리에 save
                systemRepository.save(system);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_1(@Payload Paid paid) {
        try {
            if (paid.isMe()) {
                // view 객체 조회
                Optional<System> systemOptional = systemRepository.findById(paid.getOrderId());
                if( systemOptional.isPresent()) {
                    System system = systemOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    system.setStatus(paid.getStatus());
                    // view 레파지 토리에 save
                    systemRepository.save(system);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaycanceled_then_UPDATE_2(@Payload Paycanceled paycanceled) {
        try {
            if (paycanceled.isMe()) {
                // view 객체 조회
                Optional<System> systemOptional = systemRepository.findById(paycanceled.getOrderId());
                if( systemOptional.isPresent()) {
                    System system = systemOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    system.setStatus(paycanceled.getStatus());
                    // view 레파지 토리에 save
                    systemRepository.save(system);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaycanceled_then_DELETE_1(@Payload Paycanceled paycanceled) {
        try {
            if (paycanceled.isMe()) {
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}