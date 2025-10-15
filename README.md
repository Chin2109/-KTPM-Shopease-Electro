## Project Overview:

Built a full-featured e-commerce platform simulating a real online store. Customers can browse products, manage carts, place orders, chat with staff, and receive real-time notifications. Admins manage inventory and analytics.

### Video demo: https://youtu.be/3ejhjt0IXyQ

## Responsibilities:

- Implemented JWT authentication & role-based authorization (Admin, Customer, Employee).
  Built REST APIs for product category, cart, orders, reviews, wishlist, pre-orders, and reward points.
- Integrated real-time chat (WebSocket) and notification system.
- Developed advanced search, filtering, and sorting features.
- Handled email verification with Spring Mail & Freemarker.
- Designed MySQL schema and implemented backend with Spring Boot & Hibernate.
- Created a modern user interface to smoothly communicate with the backend, ensuring a seamless user experience while interacting with Spring Boot APIs.

## Tech stack:

Java, Spring Boot, Hibernate, MySQL, JWT, WebSocket, MapStruct, Lombok, Swagger, Freemarker, Spring Mail | ReactJS, TypeScript, Mantine UI, React Query, Zustand

<!-- ## Class Diagram

Hệ thống gồm có 57 lớp chính, được chia thành 13 nhóm.

<p align="center" style="background-color: white;">
  <img src="https://user-images.githubusercontent.com/60851390/228512903-b45a45e3-7aa1-4a2b-911e-ad46a9ddb96e.svg" alt="Use Case Diagram" width="600" />
  <br>
  <em>Class Diagram</em>
</p>

| (1)                                                                                                                                                                 | (2)                                                                                                                                                                  | (3)                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| <img src="https://user-images.githubusercontent.com/60851390/228578666-f247b3fa-2ded-42aa-8581-0897b018fa4e.png" alt="Class Diagram – Address" width="200" />       | <img src="https://user-images.githubusercontent.com/60851390/228578680-f9d77837-cffe-402f-9a46-2fb919414107.png" alt="Class Diagram – Authentication" width="200" /> | <img src="https://user-images.githubusercontent.com/60851390/228578685-05e28742-03b4-4cfd-b2c5-8855fd297b89.png" alt="Class Diagram – Employee" width="200" /> |
| <img src="https://user-images.githubusercontent.com/60851390/228578691-47b00a67-92ae-43ee-87db-50e5966f8acf.png" alt="Class Diagram – Customer" width="200" />      | <img src="https://user-images.githubusercontent.com/60851390/228578698-cabe0cab-bc0c-4476-9de6-e32e21d99969.png" alt="Class Diagram – Product" width="200" />        | <img src="https://user-images.githubusercontent.com/60851390/228578707-397053b5-5cba-4093-ab4d-9d78a1fa2b7c.png" alt="Class Diagram – Cart" width="200" />     |
| <img src="https://user-images.githubusercontent.com/60851390/228578710-79138f46-97f1-409a-bf09-5bfd894ff6e8.png" alt="Class Diagram – Order" width="200" />         | <img src="https://user-images.githubusercontent.com/60851390/228578717-ba27be7b-161a-4bd5-a2e5-93d78603183a.png" alt="Class Diagram – Waybill" width="200" />        | <img src="https://user-images.githubusercontent.com/60851390/228578720-4dd437f2-54a6-47e3-b57a-3cdf321df2bb.png" alt="Class Diagram – Reward" width="200" />   |
| <img src="https://user-images.githubusercontent.com/60851390/228578723-d1d62eff-f0a3-43ee-b93c-82eadcfbae27.png" alt="Class Diagram – Chat" width="200" />          | <img src="https://user-images.githubusercontent.com/60851390/228578739-46113fbf-19c7-410e-9c4d-455f82a93377.png" alt="Class Diagram – Inventory" width="200" />      | <img src="https://user-images.githubusercontent.com/60851390/228578745-96cd55b5-f34b-4663-af31-5b128bc9a3c1.png" alt="Class Diagram – Cashbook" width="200" /> |
| <img src="https://user-images.githubusercontent.com/60851390/228578751-2ae28c8b-ef2c-489a-81e8-91f91f0853f8.png" alt="Class Diagram – Miscellaneous" width="200" /> |

## Database Diagram

Cơ sở dữ liệu của hệ thống gồm có 60 bảng.

<p align="center" style="background-color: white;">
  <img src="https://user-images.githubusercontent.com/60851390/228535444-d4945691-af6d-4514-b8bb-ae670fac7618.svg" alt="Database Diagram" />
  <br>
  <em>Database Diagram</em>
</p> -->

## Sản phẩm

Một số trang của hệ thống.

| (1)                                                                                                                                                              | (2)                                                                                                                                                             | (3)                                                                                                                                                   |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| <img src="https://user-images.githubusercontent.com/60851390/228582055-d6472b8b-78f7-4dd5-9af1-aa3347a0ef27.png" alt="Home" width="200" />                       | <img src="https://user-images.githubusercontent.com/60851390/228582074-e730fc6c-4715-47b6-9cd5-3b9f0d2aadcc.png" alt="Signup" width="200" />                    | <img src="https://user-images.githubusercontent.com/60851390/228582080-7eadf678-48a3-463e-9236-ef5d2ce1a3e0.png" alt="Signin" width="200" />          |
| <img src="https://user-images.githubusercontent.com/60851390/228582085-45c06203-e46c-4cdf-84dc-d3eb6083e989.png" alt="Category" width="200" />                   | <img src="https://user-images.githubusercontent.com/60851390/228582093-f9048abc-3a73-4a70-ad5f-6881b51929e6.png" alt="Product" width="200" />                   | <img src="https://user-images.githubusercontent.com/60851390/228582098-33324a59-a660-4271-a8d8-7782c1c8540c.png" alt="Cart" width="200" />            |
| <img src="https://user-images.githubusercontent.com/60851390/228582106-4ac300ed-cdce-4522-96b5-a6d71b491a35.png" alt="Order" width="200" />                      | <img src="https://user-images.githubusercontent.com/60851390/228582113-ca1bc913-d48c-43c9-b8f6-37c711169003.png" alt="Order Detail" width="200" />              | <img src="https://user-images.githubusercontent.com/60851390/228582118-946dd642-da1d-430b-89e0-3deab7938a79.png" alt="Review History" width="200" />  |
| <img src="https://user-images.githubusercontent.com/60851390/228582129-3e7e8d40-921a-473d-9a2b-7ef19cd48d29.png" alt="Wishlist" width="200" />                   | <img src="https://user-images.githubusercontent.com/60851390/228582136-fb87759f-fd48-4237-be28-73b8b3a28de7.png" alt="Preorder List" width="200" />             | <img src="https://user-images.githubusercontent.com/60851390/228582140-c853ed61-7b19-4cac-a404-caad1d5a8967.png" alt="User Info" width="200" />       |
| <img src="https://user-images.githubusercontent.com/60851390/228582148-8504fa75-e15f-42cf-aa15-ea4ddc01226e.png" alt="Chat" width="200" />                       | <img src="https://user-images.githubusercontent.com/60851390/228582153-99a4f8ec-0732-41ca-ac28-946fd6902b9f.png" alt="Reward" width="200" />                    | <img src="https://user-images.githubusercontent.com/60851390/228582158-878a58ce-a619-4e99-b487-ce2b61729eab.png" alt="Admin Dashboard" width="200" /> |
| <img src="https://user-images.githubusercontent.com/60851390/228582171-065d1f52-3afb-45c8-a26c-fe868ed095b3.png" alt="Product Management" width="200" />         | <img src="https://user-images.githubusercontent.com/60851390/228582179-9ed67879-4cc4-49f1-96f7-fa120d07773e.png" alt="Create Product" width="200" />            | <img src="https://user-images.githubusercontent.com/60851390/228582184-16dcdfe8-d3f6-4ff3-a603-3d9513debeee.png" alt="Update Order" width="200" />    |
| <img src="https://user-images.githubusercontent.com/60851390/228582189-48d83ff4-ac83-4d3f-83f2-435d3ca00f3b.png" alt="Reward Strategy Management" width="200" /> | <img src="https://user-images.githubusercontent.com/60851390/228582195-9bcfe194-21e3-4941-a2a5-c9d78c05950d.png" alt="Payment Method Management" width="200" /> | <img src="https://user-images.githubusercontent.com/60851390/228582203-e4e5eb14-9e25-4f2c-8936-c0373eea5d2f.png" alt="Admin Chat" width="200" />      |
