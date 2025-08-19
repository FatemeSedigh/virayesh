سیستم ایمیل Milou - مستندات فنی
📋 معرفی کلی

Milou یک سامانه کامل مدیریت ایمیل است که با جاوا و Hibernate توسعه داده شده است. این سیستم تمامی قابلیت‌های یک سرویس ایمیل حرفه‌ای از جمله احراز هویت کاربران، ارسال ایمیل، مشاهده ایمیل‌ها، پاسخ‌گویی و ارسال مجدد را فراهم می‌کند.

🏗️ معماری سیستم

ساختار پکیج‌ها

<img width="980" height="765" alt="Screenshot 1404-05-29 at 00 07 17" src="https://github.com/user-attachments/assets/938f6ab9-2ecf-4fd0-a8ee-54478bcf2d87" />

🔐 سیستم احراز هویت

کلاس Login

این کلاس مسئولیت احراز هویت کاربران را بر عهده دارد:

تکمیل خودکار ایمیل: اگر دامنه مشخص نشده باشد، به صورت خودکار "@milou.com" اضافه می‌کند
احراز هویت امن: اعتبارسنجی اطلاعات کاربر در برابر دیتابیس
مدیریت نشست: استفاده از نشست‌های Hibernate برای عملیات دیتابیس

کلاس Signup

مدیریت ثبت نام کاربران جدید:

اعتبارسنجی پسورد: حداقل ۸ کاراکتر لازم است
بررسی تکراری نبودن ایمیل: جلوگیری از ثبت ایمیل تکراری
ایجاد کاربر جدید: ذخیره اطلاعات کاربر در دیتابیس
📧 سیستم مدیریت ایمیل

کلاس Send

ارسال ایمیل جدید با قابلیت‌های زیر:

گیرندگان چندگانه: امکان ارسال به چندین گیرنده با جدا کردن با کاما
کد یکتا: تولید خودکار کد ۶ رقمی منحصر به فرد برای هر ایمیل
مدیریت خطا: کنترل خطاهای مربوط به گیرندگان ناموجود
کلاس View

مشاهده ایمیل‌ها با گزینه‌های مختلف:

همه ایمیل‌ها: نمایش تمام ایمیل‌های دریافتی
ایمیل‌های خوانده نشده: فقط ایمیل‌های خوانده نشده
ایمیل‌های ارسالی: ایمیل‌های ارسال شده توسط کاربر
مشاهده با کد: مشاهده ایمیل خاص با وارد کردن کد
کلاس Reply

پاسخ به ایمیل‌های دریافتی:

انتخاب ایمیل: با استفاده از کد ایمیل
ایجاد پاسخ: تولید خودکار موضوع با پیشوند "[Re]"
گیرندگان پاسخ: شامل فرستنده اصلی و تمام گیرندگان اصلی (به جز خود کاربر)
کلاس Forward

ارسال مجدد ایمیل:

انتخاب ایمیل: با استفاده از کد ایمیل
گیرندگان جدید: تعیین گیرندگان جدید برای ارسال مجدد
ایجاد ایمیل جدید: تولید خودکار موضوع با پیشوند "[Fw]"
🗄️ مدل‌های دیتابیس

کلاس User

مدل اطلاعات کاربران:

@Entity
@Table(name = "users")
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    // روابط با ایمیل‌های ارسالی و دریافتی
}


کلاس Email

مدل اطلاعات ایمیل:



@Entity
@Table(name = "emails")
public class Email {
    private int id;
    private User sender;
    private String code;
    private String subject;
    private String body;
    private LocalDate sentDate;
    private LocalTime sentTime;
    // لیست گیرندگان




کلاس EmailRecipient

مدل ارتباط ایمیل و گیرندگان:


@Entity
@Table(name = "email_recipients")
@IdClass(EmailRecipientId.class)
public class EmailRecipient {
    private Email email;
    private User recipient;
    private boolean isRead;
}


🔄 گردش کار برنامه

۱. اجرای برنامه

برنامه از کلاس Main شروع می‌شود و SessionFactory Hibernate را راه‌اندازی می‌کند.

۲. منوی اصلی

کاربر بین دو گزینه انتخاب می‌کند:

ورود (Login): برای کاربران موجود
ثبت نام (Signup): برای کاربران جدید
۳. احراز هویت

بررسی اعتبار کاربر
نمایش ایمیل‌های خوانده نشده در صورت موفقیت
۴. منوی عملیات

پس از ورود موفق، منوی اصلی نمایش داده می‌شود:

ارسال (S): ایجاد و ارسال ایمیل جدید
مشاهده (V): مشاهده ایمیل‌ها با گزینه‌های مختلف
پاسخ (R): پاسخ به ایمیل خاص
ارسال مجدد (F): ارسال مجدد ایمیل به گیرندگان جدید
⚙️ پیکربندی Hibernate

فایل hibernate.cfg.xml

تنظیمات اتصال به دیتابیس MySQ


<property name="hibernate.connection.url">
    jdbc:mysql://k2.liara.cloud:30055/main_email_
</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">jnmeyDzSxK9IyrbU9KaysoCY</property>



ماپینگ موجودیت‌ها

ثبت کلاس‌های مدل برای استفاده توسط Hibernate:


<mapping class="milou.model.User"/>
<mapping class="milou.model.Email"/>
<mapping class="milou.model.EmailRecipient"/>



🛡️ ویژگی‌های امنیتی

کنترل دسترسی

کاربران فقط می‌توانند ایمیل‌های خود را مشاهده کنند
بررسی مجوز برای پاسخ و ارسال مجدد ایمیل‌ها
جلوگیری از دسترسی به ایمیل‌های دیگر کاربران
اعتبارسنجی داده‌ها

بررسی طول پسورد
بررسی یکتایی ایمیل
اعتبارسنجی قالب ایمیل
🔍 کوئری‌های مهم

دریافت ایمیل‌های خوانده نشده


Query<EmailRecipient> query = session.createQuery(
    "FROM EmailRecipient er " +
    "WHERE er.recipient.id = :userId " +
    "AND er.isRead = false " +
    "AND er.email.sender.id != :userId " +
    "ORDER BY er.email.sentDate DESC, er.email.sentTime DESC",
    EmailRecipient.class);


بررسی وجود کاربر




User existingUser = session.createQuery("FROM User WHERE email = :email", User.class)
    .setParameter("email", email)
    .uniqueResult();



🎯 ویژگی‌های کلیدی




رابط کاربری خط فرمان: ساده و کاربرپسند
مدیریت نشست خودکار: با استفاده از try-with-resources
تاریخ و زمان خودکار: ثبت خودکار زمان ارسال ایمیل
کد یکتا: تولید خودکار کد ۶ رقمی برای هر ایمیل
پشتیبانی از چندگیرنده: ارسال به چندین کاربر simultaneously
مدیریت وضعیت خواندن: پیگیری ایمیل‌های خوانده شده/نشده
📋 نکات فنی مهم

الگوهای طراحی

Single Responsibility Principle: هر کلاس یک مسئولیت مشخص دارد
Dependency Injection: تزریق وابستگی‌ها از طریق constructor
DAO Pattern: جداسازی لایه دسترسی به داده
مدیریت حافظه

استفاده از try-with-resources برای مدیریت خودکار منابع
بستن خودکار Session های Hibernate
مدیریت صحیح تراکنش‌ها
مدیریت خطا

کنترل خطاهای دیتابیس
Handling کاربران ناموجود
مدیریت خطاهای ورودی کاربر
این مستندات تمامی جنبه‌های فنی پروژه را پوشش می‌دهد و برای ارائه به TA کاملاً مناسب است.










