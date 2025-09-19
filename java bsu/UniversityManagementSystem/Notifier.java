package UniversityManagementSystem;

import java.util.Set;

public class Notifier {
    private Set<Notifiable> notifiables;

    public Notifier(Set<Notifiable> notifiables) {
        this.notifiables = notifiables;
    }

    public void doNotifyAll(String massage) {
        notifiables.forEach(notifiable -> notifiable.notify(massage));
    } 
}
