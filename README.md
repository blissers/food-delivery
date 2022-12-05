# food-delivery
![image](https://user-images.githubusercontent.com/487999/79708354-29074a80-82fa-11ea-80df-0db3962fb453.png)

# 예제 - 음식배달

본 예제는 MSA/DDD/Event Storming/EDA 를 포괄하는 분석/설계/구현/운영 전단계를 커버하도록 구성한 예제입니다.
이는 클라우드 네이티브 애플리케이션의 개발에 요구되는 체크포인트들을 통과하기 위한 예시 답안을 포함합니다.

# 서비스 시나리오

기능적 요구사항
1. 고객이 메뉴를 선택하여 주문한다.
2. 고객이 선택한 메뉴에 대해 결제한다.
3. 주문이 되면 주문 내역이 입점상점주인에게 주문정보가 전달된다.
4. 상점주는 주문을 수락하거나 거절할 수 있다.
5. 상점주는 요리시작때와 완료 시점에 시스템에 상태를 입력한다.
6. 고객은 아직 요리가 시작되지 않은 주문은 취소할 수 있다.
7. 요리가 완료되면 고객의 지역 인근의 라이더들에 의해 배송건 조회가 가능하다.
8. 라이더가 해당 요리를 pick 한후, pick했다고 앱을 통해 통보한다.
9. 고객이 주문상태를 중간중간 조회한다.
10. 주문상태가 바뀔 때 마다 카톡으로 알림을 보낸다.
11. 고객이 요리를 배달 받으면 배송확인 버튼을 탭하여, 모든 거래가 완료된다.

# 체크포인트
# 1. Saga (Pub / Sub) : 주문처리를 하였을 때 status가 OrderPlace로 변경

*서비스의 주문처리

![image](https://user-images.githubusercontent.com/38126952/205559865-dee0740d-16a5-4ac4-94a5-fdd728eb3860.png)

*서비스의 배달처리

![image](https://user-images.githubusercontent.com/38126952/205561260-4e01a135-43ef-4a3a-9b9b-7e584792c002.png)

*주문상태 확인

![image](https://user-images.githubusercontent.com/38126952/205561466-c9cf87b0-f28f-4eee-87fb-cb3f51117c17.png)

# 2. CQRS : OrderStatus를 통해 주문상태가 변경되는지 확인

![image](https://user-images.githubusercontent.com/38126952/205567110-2b58ccc8-6a89-4518-ba89-d5965eca55e0.png)

# 3. Compensation / Correlation : 주문을 취소

orderId : 1 인 주문을 취소하면 더이상 orderId : 1 의 주문에 대한 status값이 안나온다.
![image](https://user-images.githubusercontent.com/38126952/205569288-4d78ef33-3688-4dbe-9493-d5ab1d45cd63.png)

# 4. Request / Response

주문취소시 요리가 시작하지 않았다면 결제취소한다.
![image](https://user-images.githubusercontent.com/38126952/205573114-98711337-04e8-4ce6-a3ca-55cd4d56d7c3.png)

동기호출 :

![image](https://user-images.githubusercontent.com/38126952/205573797-0224097c-9cca-4978-9074-a8785eebb579.png)

비동기호출 :

![image](https://user-images.githubusercontent.com/38126952/205574711-6424ddb6-53bc-45e3-890c-a653f8f83c5f.png)

# 5. Circuit Breaker
Circuit Breaker를 

![image](https://user-images.githubusercontent.com/38126952/205576427-6583666f-e486-47f0-8ea4-fe2a96967dad.png)

# 6. Gateway / Ingress
