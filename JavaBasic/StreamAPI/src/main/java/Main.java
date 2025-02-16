import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Filter: Lọc danh sách
        List<Integer> number = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = number.stream().filter(e -> e % 2 == 0).collect(Collectors.toList());
        System.out.println(evenNumbers);

        // Map: chuyển từ định dạng này sang định dạng khác, không làm thay đổi cái mảng cũ
        List<String> names = Arrays.asList("John", "Jane", "Alice", "Bob");
        System.out.println(names.stream().map(String::length).collect(Collectors.toList()));

        System.out.println(names.stream().map(n -> n.concat(" hi")).collect(Collectors.toList()));

        List<String> converIntergerToString = number.stream().map(String::valueOf).collect(Collectors.toList());
        System.out.println("Type: " + converIntergerToString.getClass().getName() + " Value: " + converIntergerToString);

        // Flat map: ánh xạ từng phần tử thành một stream, sau đó gọp lại thành một stream duy nhất
        List<String> sentences = Arrays.asList("Hello world", "Java stream flatMap", "Example here");
        // Tách từng câu thành danh sách từ
        System.out.println(sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList()));

        //Nếu dùng map thì nó sẽ không thể gọp các stream lại thành một stream duy nhất được
        List<String> sentences_1 = Arrays.asList("Hello world", "Java stream flatMap", "Example here");
        // Tách từng câu thành danh sách từ
//        List<String> words_1 = sentences.stream()
//                .map(sentence -> Arrays.stream(sentence.split(" ")))
//                .collect(Collectors.toList());
//        System.out.println(words);

        // Distinct: loại bỏ các phần tử trùng lặp, đưa về phần tử duy nhất
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 1, 2, 3, 4, 5, 5);
        System.out.println(numbers.stream().distinct().collect(Collectors.toList()));

        // Sorted: sắp xếp, mặc định là sắp xếp tăng dần
        System.out.println(numbers.stream().distinct().sorted().collect(Collectors.toList()));
        // Tùy chỉnh sắp xếp: sắp xếp giảm dần
        System.out.println(numbers.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        // Sắp xếp theo độ dài chuỗi
        System.out.println(sentences.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList()));

        //Sắp xếp danh sách các object theo thuộc tính
        List<Person> personList = Arrays.asList(
                new Person("01", "Tung", 20),
                new Person("02", "Nam", 19),
                new Person("02", "Nam", 21)
        );

        //peek: thực hiện mỗi lần duyệt thì in ra phần tử được duyệt đó
        System.out.println(
                personList.stream()
                        .sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder()).thenComparing(Person::getName, Comparator.reverseOrder()))
                        .collect(Collectors.toList()));
        System.out.println(number.stream().filter(n -> n % 2 == 0).peek(System.out::println).collect(Collectors.toList()));

        // limit/ skip
        System.out.println(number.stream().skip(1).limit(2).collect(Collectors.toList()));

        // forEach: thực hiện hành động trên mỗi phần tử
//        number.stream().forEach(System.out::println);

        // toArray: chuyển stream thành một mảng
//        Integer[] array = number.stream().toArray(Integer[]::new);

        //reduce: tính toán kết quả của các phần tử trong stream
        System.out.println(number.stream().filter(a -> a % 2 == 0).reduce(0, Integer::sum));
        // Cách 2: ít sử dụng hơn
//        System.out.println(number.stream().filter(a -> a % 2 == 0).reduce(0, (a, b) -> a + b));
        //Phép trừ
        System.out.println(number.stream().filter(a -> a % 2 == 0).reduce((a, b) -> a - b));

        //collect: thu thập dữ liệu từ stream,chuyển nó về kiểu dữ liệu mong muốn List, Set, Map hoặc các giá trị tổng hợp

        // Trả về list
        System.out.println(number.stream().filter(a -> a % 2 == 0).collect(Collectors.toList()));

        // Trả về set: loại bỏ các phần tử trùng lặp
//        System.out.println(numbers.stream().collect(Collectors.toSet()));
        //Hoặc
        System.out.println(new HashSet<>(numbers));

        // Trả về map: key:value
        Map<Integer, String> list = personList.stream().collect(Collectors.toMap(Person::getAge, Person::getName));
        System.out.println(list);

        //Joining: gọp các phần tử lại thành một chuỗi
//        System.out.println(names.stream().collect(Collectors.joining(",")));
        // Cach 2:
        System.out.println(String.join(",", names));

        //Tính toán tổng hợp: sum, count, average
        System.out.println(personList.stream().collect(Collectors.averagingInt(Person::getAge)));
        System.out.println((Integer) personList.stream().mapToInt(Person::getAge).sum());

        // Gom nhóm dữ liệu
        Map<Integer, List<Person>> groupByAge = Stream.of(
                        new Person("1", "An", 20),
                        new Person("2", "Bình", 25),
                        new Person("3", "Châu", 25)
                )
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(groupByAge);


        //Matching - khớp
        //anyMatch: kiểm tra xem có phần tử nào trong mảng thỏa mãn điều kiện không
        System.out.println(names.stream().anyMatch(n -> n.equals("John")));
        //allMatch
        System.out.println(names.stream().allMatch(n -> n.equals("John")));
        //noneMatch
        System.out.println(names.stream().noneMatch(n -> n.equals("John")));

        //Find: tìm kiếm
        System.out.println(names.stream().findAny());
        System.out.println(names.stream().findFirst());

        //orElse: cung cấp giá trị mặc định: nếu không tìm thấy thì return null
        System.out.println(names.stream().findAny().orElse(null));

        //Count- đếm
        System.out.println(number.stream().filter(a -> a % 2 == 0).count());
        System.out.println(number.stream().max(Integer::compareTo));
        System.out.println(number.stream().min(Integer::compareTo));

    }
}
