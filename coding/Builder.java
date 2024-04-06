public class Builder {
    // 产品类：电脑
    class Computer {
        private String cpu;
        private String memory;
        private String hardDisk;

        public void setCpu(String cpu) {
            this.cpu = cpu;
        }

        public void setMemory(String memory) {
            this.memory = memory;
        }

        public void setHardDisk(String hardDisk) {
            this.hardDisk = hardDisk;
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "cpu='" + cpu + '\'' +
                    ", memory='" + memory + '\'' +
                    ", hardDisk='" + hardDisk + '\'' +
                    '}';
        }
    }

    // 抽象建造者接口
    interface ComputerBuilder {
        void buildCpu();
        void buildMemory();
        void buildHardDisk();
        Computer getResult();
    }

    // 具体建造者：台式电脑建造者
    class DesktopComputerBuilder implements ComputerBuilder {
        private Computer computer;

        public DesktopComputerBuilder() {
            computer = new Computer();
        }

        @Override
        public void buildCpu() {
            computer.setCpu("Intel i7");
        }

        @Override
        public void buildMemory() {
            computer.setMemory("16GB DDR4");
        }

        @Override
        public void buildHardDisk() {
            computer.setHardDisk("512GB SSD");
        }

        @Override
        public Computer getResult() {
            return computer;
        }
    }

    // 指挥者：电脑装配员
    class ComputerDirector {
        public Computer construct(ComputerBuilder builder) {
            builder.buildCpu();
            builder.buildMemory();
            builder.buildHardDisk();
            return builder.getResult();
        }
    }

    // 客户端代码
    public class BuilderPatternExample {
        public static void main(String[] args) {
            // 创建指挥者
            ComputerDirector director = new ComputerDirector();

            // 创建具体建造者
            ComputerBuilder desktopBuilder = new DesktopComputerBuilder();

            // 指挥具体建造者构建电脑对象
            Computer desktopComputer = director.construct(desktopBuilder);

            // 输出构建完成的电脑对象
            System.out.println("Desktop Computer: " + desktopComputer);
        }
    }
}
