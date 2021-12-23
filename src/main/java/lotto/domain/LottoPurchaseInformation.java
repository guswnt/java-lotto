package lotto.domain;

import lotto.exception.LottoException;

import java.util.List;

public class LottoPurchaseInformation {
    private final static int LOTTO_TICKET_PRICE = 1000;

    private final int numberOfManualTicket;
    private final int numberOfAutomaticTicket;
    private final List<String> manuallyPickedLottoTickets;
    private final int lottoTicketsPrice;

    private LottoPurchaseInformation(int numberOfManualTicket, int numberOfAutomaticTicket, List<String> manuallyPickedLottoTickets, int lottoTicketsPrice) {
        this.numberOfManualTicket = numberOfManualTicket;
        this.numberOfAutomaticTicket = numberOfAutomaticTicket;
        this.manuallyPickedLottoTickets = manuallyPickedLottoTickets;
        this.lottoTicketsPrice = lottoTicketsPrice;
    }

    public static LottoPurchaseInformation of(int money, List<String> manuallyPickedLottoTickets) {
        int numberOfLottoTicket = money / LOTTO_TICKET_PRICE;
        int numberOfManualTicket = manuallyPickedLottoTickets.size();
        validateNumberOfLottoTicket(numberOfLottoTicket, numberOfManualTicket);
        int numberOfAutomaticTicket = numberOfLottoTicket - numberOfManualTicket;
        return new LottoPurchaseInformation(numberOfManualTicket, numberOfAutomaticTicket, manuallyPickedLottoTickets, numberOfLottoTicket * LOTTO_TICKET_PRICE);
    }

    private static void validateNumberOfLottoTicket(int numberOfLottoTicket, int numberOfManualTicket) {
        if (numberOfLottoTicket < numberOfManualTicket) {
            throw new LottoException("로또 수동 입력 개수가 구입 금액을 초과했습니다");
        }
    }

    public int lottoTicketsPrice() {
        return lottoTicketsPrice;
    }

    public int numberOfManualTicket() {
        return numberOfManualTicket;
    }

    public int numberOfAutomaticTicket() {
        return numberOfAutomaticTicket;
    }

    public List<String> manuallyPickedLottoTickets() {
        return manuallyPickedLottoTickets;
    }

}
