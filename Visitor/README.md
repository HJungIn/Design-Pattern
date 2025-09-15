### Visitor 패턴

👍 Visitor 패턴은 객체 구조와 동작(처리 로직)을 분리하는 데 쓰이는 전형적인 행동(Behavioral) 디자인 패턴
* 비지터 패턴은 방문자와 방문 공간을 분리하여, 방문 공간이 방문자를 맞이할 때, 이후에 대한 행동을 방문자에게 위임하는 패턴
* 행위(Behavioral) 패턴 - 객체 간의 상호작용, 책임 분배에 초점.
* 구조와 연산을 분리, 이중 디스패치 활용
* 주로 객체 구조 내에 있는 여러 유형의 객체를 방문하면서 특정 작업을 수행하거나 동작을 수행하기 위해 사용됨
   * 데이터 구조보다 알고리즘이 더 자주 바뀌는 경우 -> 즉, 방문공간은 어느정도 정해져있고 방문자가 더 자주 바뀌는 경우 (ex. A도 들리고 B도 들리고 C도 들리는)

**📌 Visitor 패턴 설명**
* 핵심 아이디어
  * 객체 구조(예: Element 들)는 고정되어 있고, 그 위에서 동작하는 새로운 연산(로직) 을 유연하게 추가하고 싶을 때 사용
* 방법
  * 객체 구조에 accept(Visitor visitor) 메서드를 두고, Visitor 가 각 구체 객체(ConcreteElement)를 방문해서 작업을 수행하도록 위임

* 장점
  * 객체 구조를 건드리지 않고도 새로운 연산을 추가 가능
* 단점
  * 구조가 자주 변하면 Visitor 유지보수 비용이 커짐 (Element 종류가 늘어나면 Visitor 인터페이스도 바뀜)

---

**✅ Visitor 패턴을 쓰면 좋은 상황**
1. 객체 구조는 안정적이고, 연산은 자주 추가되는 경우
    * 예: 컴파일러의 AST(Abstract Syntax Tree)
        * 노드 구조(Expression, Statement 등)는 안정적
        * 하지만 타입 검사, 코드 최적화, 코드 생성 같은 연산은 계속 추가됨
2. 객체 구조와 로직을 분리해서 관심사를 분리하고 싶을 때
    * 데이터 구조는 단순히 “상태(state)”만 담고,
    * Visitor가 그 위에 “행동(behavior)”을 정의
3. 여러 타입의 객체에 대해 같은 연산을 적용해야 할 때
    * 예: 쇼핑몰 결제 시스템
        * 상품(Book, Fruit, Electronic 등)이 다 달라도,
        * “가격 계산” 로직은 하나의 Visitor로 묶어 실행
4. 다형성만으로 해결하기 어려운 경우
    * if (obj instanceof A) ... else if (obj instanceof B) 같은 코드 대신
    * Visitor를 쓰면 **이중 디스패치(Double Dispatch)**로 깔끔하게 처리


**❌ Visitor 패턴이 오히려 안 좋은 상황**
1. 객체 구조(클래스 계층)가 자주 바뀌는 경우
    * Element(예: Book, Fruit 등)가 하나만 추가되어도
    * Visitor 인터페이스와 모든 구현체를 전부 수정해야 함 → 유지보수 악몽 😅
2. 단순히 다형성(Polymorphism)만으로 충분한 경우
    * 예: Book/Fruit 각각에 getPrice() 메서드를 두면
    * Visitor 패턴 없이도 계산 가능
3. 구조와 연산이 모두 자주 바뀌는 경우
    * 구조가 변해도 Visitor 수정해야 하고,
    * 연산이 늘어나도 Visitor가 늘어나서 코드 복잡도 증가
4. 작은 프로젝트나 단순 CRUD 앱
    * 오히려 Visitor가 불필요한 계층/코드 복잡성을 추가

---

**📌 클래스 다이어그램 (간단화)**
```
    Visitor
     ├─ ConcreteVisitorA
     ├─ ConcreteVisitorB
    
    Element
     ├─ ConcreteElementA
     ├─ ConcreteElementB
```
    
* ```Element.accept(Visitor v)``` → ```v.visit(this)```
* ```Visitor.visit(ConcreteElementX)``` → 구체 동작 수행

**📌 Java 예제**
1. Visitor 인터페이스
    ```java
    interface Visitor {
        void visit(Book book);
        void visit(Fruit fruit);
    }
    ```

2. Element 인터페이스
    ```java
    interface ItemElement {
        void accept(Visitor visitor);
    }
    ```
   
3. 구체 Element
    ```java
    class Book implements ItemElement {
        private String title;
        private int price;
    
        public Book(String title, int price) {
            this.title = title;
            this.price = price;
        }
    
        public int getPrice() { return price; }
        public String getTitle() { return title; }
    
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    
    class Fruit implements ItemElement {
        private String name;
        private int pricePerKg;
        private int weight;
    
        public Fruit(String name, int pricePerKg, int weight) {
            this.name = name;
            this.pricePerKg = pricePerKg;
            this.weight = weight;
        }
    
        public int getPricePerKg() { return pricePerKg; }
        public int getWeight() { return weight; }
        public String getName() { return name; }
    
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    ```

4. Concrete Visitor 구현
    ```java
    class ShoppingCartVisitor implements Visitor {
        @Override
        public void visit(Book book) {
            System.out.println("Book: " + book.getTitle() + " costs " + book.getPrice());
        }
    
        @Override
        public void visit(Fruit fruit) {
            int cost = fruit.getPricePerKg() * fruit.getWeight();
            System.out.println("Fruit: " + fruit.getName() + " costs " + cost);
        }
    }
    ```

5. 실행 코드
    ```java
    public class VisitorPatternExample {
        public static void main(String[] args) {
            ItemElement[] items = new ItemElement[]{
                    new Book("Design Patterns", 5000),
                    new Fruit("Apple", 200, 3)
            };
    
            Visitor visitor = new ShoppingCartVisitor();
            for (ItemElement item : items) {
                item.accept(visitor);
            }
        }
    }
    ```

6. 결과
    ```
    Book: Design Patterns costs 5000
    Fruit: Apple costs 600
    ```

📌 요약
* Visitor 패턴은 Element 구조는 고정, 로직은 확장 가능
* accept()를 통해 Visitor가 이중 디스패치(double dispatch) 수행
* 새로운 연산(Visitor) 추가는 쉽지만, Element 종류가 자주 바뀌면 오히려 불리
* 활용법 : 방문자 패턴을 사용하면 객체 구조를 수정하지 않고도 새로운 작업을 추가하거나 객체 구조를 순회하는 방법을 변경
   * ex. 쇼핑몰에서 구매한 상품들의 총 가격을 계산하는 프로그램 => 책, 음반, 영화 등 다양한 상품이 있으며, 각각의 상품은 가격을 계산하는 방법

##### 참조
* 이론 : https://dailyheumsi.tistory.com/216
* 예제 : https://shan0325.tistory.com/41