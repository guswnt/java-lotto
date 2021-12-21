package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static lotto.domain.LottoProperties.*;

public class LottoTicketFactory {
    public static final int LOTTO_NUMBER_MIN_INDEX = 0;
    private static final int LOTTO_NUMBER_MAX_INDEX = NUMBER_OF_LOTTO_NUMBERS - 1;
    private static final List<LottoNumber> NUMBERS = IntStream
            .rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .mapToObj(LottoNumber::from)
            .collect(toList());

    private LottoTicketFactory() {
    }

//    public static LottoTickets buy(String money, int numberOfManuallyPickedLottoTickets, List<LottoTicket> manuallyPickedLottoTickets) {
//        return buy(toInt(money), numberOfManuallyPickedLottoTickets, manuallyPickedLottoTickets);
//    }
//
//    private static int toInt(String moneyString) {
//        try {
//            return Integer.parseInt(moneyString);
//        } catch (NumberFormatException e) {
//            throw new LottoException("구입할 로또 가격을 숫자로 입력해야합니다.");
//        }
//    }

    public static LottoTickets buy(LottoPurchaseInformation lottoPurchaseInformation, List<LottoTicket> manuallyPickedLottoTickets) {
        int numberOfAutomaticallyPickedLottoTickets = lottoPurchaseInformation.numberOfAutomaticallyPickedLottoTicket();
        List<LottoTicket> lottoTicketList = new ArrayList<>(manuallyPickedLottoTickets);
        for (int i = 0; i < numberOfAutomaticallyPickedLottoTickets; i++) {
            lottoTicketList.add(generate());
        }
        return new LottoTickets(lottoTicketList, lottoPurchaseInformation.lottoTicketsPrice());
    }

    private static LottoTicket generate() {
        Collections.shuffle(NUMBERS);
        List<LottoNumber> lottoNumbers = IntStream.rangeClosed(LOTTO_NUMBER_MIN_INDEX, LOTTO_NUMBER_MAX_INDEX)
                .mapToObj(NUMBERS::get)
                .sorted()
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));

        return LottoTicket.from(lottoNumbers);
    }

}
