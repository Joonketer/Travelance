package com.easyone.travelance.domain.travel.service;

import com.easyone.travelance.domain.member.entity.Member;
import com.easyone.travelance.domain.member.respository.ProfileRepository;
import com.easyone.travelance.domain.payment.entity.Payment;
import com.easyone.travelance.domain.payment.repository.PaymentRepository;
import com.easyone.travelance.domain.travel.dto.PaymentResponseDto;
import com.easyone.travelance.domain.travel.entity.TravelRoom;
import com.easyone.travelance.domain.travel.entity.TravelRoomMember;
import com.easyone.travelance.domain.travel.repository.TravelRoomMemberRepository;
import com.easyone.travelance.domain.travel.repository.TravelRoomRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TravelPaymentService {

    private final PaymentRepository paymentRepository;
    private final TravelRoomMemberRepository travelRoomMemberRepository;
    private final TravelRoomRepository travelRoomRepository;
    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public List<PaymentResponseDto> findByTravelId(Member member, Long roomId) {
        List<Payment> payments = paymentRepository.findByTravelRoomId(roomId);
        return getPaymentResponseDtos(member, roomId, payments);

    }

    @Transactional(readOnly = true)
    public List<PaymentResponseDto> findByTravelIdAndMemberId(Member member, Long roomId) {
        Long memberId = member.getId();
        List<Payment> payments = paymentRepository.findByTravelRoomIdAndMemberId(roomId, memberId);
        return getPaymentResponseDtos(member, roomId, payments);
    }

    @NotNull
    private List<PaymentResponseDto> getPaymentResponseDtos(Member member, Long roomId, List<Payment> payments) {
        TravelRoom travelRoom = travelRoomRepository.findByIdAndMemberId(roomId, member.getId())
                .orElseThrow(()-> new IllegalArgumentException("사용자의 여행방이 없습니다. id =" + roomId));

        TravelRoomMember travelRoomMember = travelRoomMemberRepository.findByTravelRoomAndMember(travelRoom, member)
                .orElseThrow(()-> new IllegalArgumentException("사용자가" + member.getId() + "이 여행방에 없습니다" + roomId));

        String profileUrl = profileRepository.findByMemberAndTravelRoom(member, travelRoom).getProfileUrl();

        ArrayList<PaymentResponseDto> paymentArrayList = new ArrayList<>();

        for (Payment payment: payments) {
            PaymentResponseDto paymentResponseDto = new PaymentResponseDto(payment, member, travelRoomMember.getTravelNickName(), profileUrl);
            paymentArrayList.add(paymentResponseDto);
        }
        return paymentArrayList;
    }

    @Transactional(readOnly = true)
    public Long TotalPriceTravelId(Long roomId) {
        List<Payment> payments = paymentRepository.findByTravelRoomId(roomId);

        if (payments.size()!=0) {
            Long totalprice = payments.stream()
                    .mapToLong(Payment::getPaymentAmount)
                    .sum();
            return totalprice;
        }
        else {
            return 0L;
        }

    }



}
