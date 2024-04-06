import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class question {
    static class Task implements Runnable {
        private String name;
        private int id;

        public Task(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread:" + id + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        String str = "abbbadeffgh";
        Stack<Character> stack = new Stack<>();
        // stack.push(str.charAt(0));
        for(int i = 0; i < str.length();) {
            if(!stack.isEmpty()) {
                char top = stack.peek();
                if(str.charAt(i) != top) stack.push(str.charAt(i++));
                else {
                    while(str.charAt(i) == top) {
                        i++;
                    }
                    stack.pop();
                }
            } else {
                stack.push(str.charAt(i++));
            }
            
        }
        Stack<Character> ans = new Stack<>();
        while(!stack.isEmpty()) {
            ans.push(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while(!ans.isEmpty()) sb.append(ans.pop());
        System.out.println(sb.toString());

        System.out.println(Double.parseDouble("111.444"));

        ThreadPoolExecutor threadpool = new ThreadPoolExecutor(10, 16, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for(int i = 1; i <= 10; i++) {
            Task task = new Task(i, "task");
            threadpool.execute(task);
        }
    }
}
