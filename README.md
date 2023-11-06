# 숙소 예약 시스템
## 1. 개요
23년 2학기 빅데이터 JPA 개인 텀 프로젝트의 일환으로 개발

## 2. 사용 기술 스택
Java17, H2 데이터베이스, JPA(Hibernate) 사용

## 3. 요구사항
1. 상속, 값 타입, MappedSuperclass 적용 
2. 호스트는 숙소를 등록 가능(registHouse)
3. 호스트는 특정 기간에 정량 할인이나 정률 할인을 적용 가능(calPrice)
4. 게스트는 조건에 맞는 숙소를 조회 가능(findHouse)
5. 게스트는 선택한 숙소의 상세 정보 조회 가능(houseDetail)
6. 체크인, 체크아웃 날짜와 인원을 입력하여 예약 가능(bookHouse)
7. 게스트는 예약한 숙소 취소 가능(cancelReserve)
8. 게스트는 예약 기록 조회 가능(ReservationHistory)
9. 게스트는 체크아웃이 완료된 숙소에 별점과 후기 작성 가능(addComments)
10. 호스트는 지정한 달의 매출 확인 가능(showReserveState, calRevenue)
