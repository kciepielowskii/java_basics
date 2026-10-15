package lesson7;

public class Developer extends Employee {
    private final int countOvertime;
    private final double hourlyRate;

    public Developer (String name, double baseSalary, int countOvertime, double hourlyRate) {
        super(name, baseSalary);
        if (countOvertime < 0) {
            throw new IllegalArgumentException("The overtime must be postive");
        } else if (hourlyRate <= 0) {
            throw new IllegalArgumentException("The hourlyRate must be greather than zero");
        }
        this.countOvertime = countOvertime;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + (countOvertime * hourlyRate);
    }

    // Getters
    public int getCountOvertime() {
        return countOvertime;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
