import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

// Класс УчебнаяГруппа
class StudyGroup {
    private String groupName;

    public StudyGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}

// Класс Поток, содержащий список учебных групп и реализующий интерфейс Iterable
class Stream implements Iterable<StudyGroup> {
    private List<StudyGroup> studyGroups;

    public Stream() {
        this.studyGroups = new ArrayList<>();
    }

    public void addGroup(StudyGroup group) {
        studyGroups.add(group);
    }

    public int getGroupCount() {
        return studyGroups.size();
    }

    @Override
    public Iterator<StudyGroup> iterator() {
        return studyGroups.iterator();
    }
}

// Класс StreamComparator, реализующий сравнение количества групп, входящих в Поток
class StreamComparator implements Comparator<Stream> {
    @Override
    public int compare(Stream s1, Stream s2) {
        return Integer.compare(s1.getGroupCount(), s2.getGroupCount());
    }
}

// Класс ПотокСервис, добавляющий метод сортировки списка потоков
class StreamService {
    public void sortStreams(List<Stream> streams) {
        Collections.sort(streams, new StreamComparator());
    }
}

// Класс Контроллер, содержащий сервис и метод сортировки потоков
class Controller {
    private StreamService streamService;

    public Controller() {
        this.streamService = new StreamService();
    }

    public void sortStreams(List<Stream> streams) {
        streamService.sortStreams(streams);
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        // Testing Stream and StreamService
        StudyGroup group1 = new StudyGroup("Group A");
        StudyGroup group2 = new StudyGroup("Group B");
        StudyGroup group3 = new StudyGroup("Group C");

        Stream stream1 = new Stream();
        stream1.addGroup(group1);
        stream1.addGroup(group2);

        Stream stream2 = new Stream();
        stream2.addGroup(group3);

        Stream stream3 = new Stream();
        stream3.addGroup(group1);
        stream3.addGroup(group2);
        stream3.addGroup(group3);

        List<Stream> streams = new ArrayList<>();
        streams.add(stream1);
        streams.add(stream2);
        streams.add(stream3);

        Controller controller = new Controller();
        System.out.println("Before sorting:");
        for (Stream stream : streams) {
            System.out.println("Stream with " + stream.getGroupCount() + " groups.");
        }

        controller.sortStreams(streams);

        System.out.println("\nAfter sorting:");
        for (Stream stream : streams) {
            System.out.println("Stream with " + stream.getGroupCount() + " groups.");
        }
    }
}
