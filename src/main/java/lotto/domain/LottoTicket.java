package lotto.domain;

import lotto.exception.LottoException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import static lotto.domain.LottoProperties.NUMBER_OF_LOTTO_NUMBERS;

public class LottoTicket {

    private static final String SPLITTER = ", ";

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket from(List<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket from(Integer... numbers) {
        List<LottoNumber> lottoNumberList = Arrays.stream(numbers)
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumberList);
    }

    public static LottoTicket from(String... numbers) {
        List<LottoNumber> lottoNumberList = Arrays.stream(numbers)
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumberList);
    }


    public static LottoTicket from(String numbers) {
        try {
            String[] lottoNumbers = numbers.split(SPLITTER);
            return LottoTicket.from(lottoNumbers);
        } catch (PatternSyntaxException e) {
            throw new LottoException("로또 번호 입력 형식이 틀렸습니다");
        }
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != NUMBER_OF_LOTTO_NUMBERS) {
            throw new LottoException("로또 번호는 6개여야 합니다.");
        }
    }

    public LottoRank getLottoRank(LottoWinningNumbers lottoWinningNumbers) {
        return LottoRank.valueOf(
                countOfMatch(lottoWinningNumbers),
                matchesBonus(lottoWinningNumbers)
        );
    }

    public int countOfMatch(LottoWinningNumbers lottoWinningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(lottoWinningNumbers::contain)
                .count();
    }

    public boolean matchesBonus(LottoWinningNumbers lottoWinningNumbers) {
        return lottoWinningNumbers.matchesBonus(lottoNumbers);
    }
    
    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

}
