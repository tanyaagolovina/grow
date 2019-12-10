package task1;


import task1.thirdpartyjar.CollectionService;
import task1.thirdpartyjar.Message;
import task1.thirdpartyjar.NotificationManager;
import task1.thirdpartyjar.Order;

public class CollectOrderService implements OrderService {

    private CollectionService collectionService;
    private NotificationManager notificationManager;
    private static final int CRITICAL_NOTIFICATION_LEVEL = 1;
    private static final int INFO_NOTIFICATION_LEVEL = 4;

    public void submitOrder(Order order) {
        if (collectionService.isEligibleForCollection(order))
            notificationManager.notifyCustomer(Message.READY_FOR_COLLECT, INFO_NOTIFICATION_LEVEL);
        else
            notificationManager.notifyCustomer(Message.IMPOSSIBLE_TO_COLLECT, CRITICAL_NOTIFICATION_LEVEL);
    }

    public void setCollectionService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
}
