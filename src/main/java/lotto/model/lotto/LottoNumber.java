package lotto.model.lotto;

public class LottoNumber implements Comparable<LottoNumber> {
    private final static String RANGE_ERROR_MESSAGE = "유효한 로또 번호가 아닙니다.";
    private final static int MIN_NUMBER = 0;
    private final static int MAX_NUMBER = 45;
    private int number;

    public LottoNumber(int number) {
        if (isNotValidRange(number)) {
            throw new IllegalArgumentException(RANGE_ERROR_MESSAGE);
        }
        this.number = number;
    }

    private boolean isNotValidRange(int inputNumber) {
        return inputNumber <= MIN_NUMBER || MAX_NUMBER < inputNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LottoNumber)) {
            return false;
        }
        LottoNumber inputNumber = (LottoNumber) obj;
        return inputNumber.number == number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
