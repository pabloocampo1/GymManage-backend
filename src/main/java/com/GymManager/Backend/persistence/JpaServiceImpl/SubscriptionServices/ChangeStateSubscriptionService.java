package com.GymManager.Backend.persistence.JpaServiceImpl.SubscriptionServices;

import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChangeStateSubscriptionService {

    @Autowired
    private final SubscriptionPersistencePort susSubscriptionPersistencePort;

   //  @Scheduled(cron = "0 0 1 * * *")
    @Scheduled(cron = "*/20 * * * * *")
    public void checkAndChangeStatusSubscription() {
        List<SubscriptionEntity> allSubscriptions = this.susSubscriptionPersistencePort.findAll();
        for (SubscriptionEntity subscription : allSubscriptions) {
            if (subscription.getFinishDate().isBefore(LocalDateTime.now()) &&
                    subscription.getFinishDate() != null &&
                    Boolean.TRUE.equals(subscription.getStatus())) {
                subscription.setStatus(false);
                this.susSubscriptionPersistencePort.saveDirect(subscription);
            }
        }
    }

}
