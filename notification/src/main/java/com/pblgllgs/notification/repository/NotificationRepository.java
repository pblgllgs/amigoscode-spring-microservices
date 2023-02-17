package com.pblgllgs.notification.repository;

import com.pblgllgs.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer > {
}
