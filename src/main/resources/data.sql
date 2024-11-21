TRUNCATE TABLE english_transcript;

SET SESSION cte_max_recursion_depth = 1000000;

INSERT INTO english_transcript (title, content, youtube_url, created_at)
WITH RECURSIVE cte (n) AS
                   (
                       SELECT 1
                       UNION ALL
                       SELECT n + 1 FROM cte WHERE n < 1000000
                   )
SELECT
    CONCAT(
            'EP', n, ' - ',
            CASE
                WHEN n % 5 = 1 THEN '영어 표현에 대한 '
                WHEN n % 5 = 2 THEN '일상 대화에 대한 '
                WHEN n % 5 = 3 THEN '비즈니스 영어에 대한 '
                WHEN n % 5 = 4 THEN '발음 교정에 대한 '
                ELSE '영어 문법에 대한 '
                END,
            CASE
                WHEN n % 4 = 1 THEN '간단한 가이드'
                WHEN n % 4 = 2 THEN '자세한 가이드'
                WHEN n % 4 = 3 THEN '필수적인 팁'
                ELSE '유익한 조언'
                END
        ) AS title,

    CONCAT(
            CASE
                WHEN n % 5 = 1 THEN '이번 글에서는 영어 표현을 어떻게 자연스럽게 사용할 수 있는지에 대해 다룹니다. '
                WHEN n % 5 = 2 THEN '일상 대화에서 자주 쓰이는 영어 표현을 배워보세요. '
                WHEN n % 5 = 3 THEN '비즈니스 상황에서 유용한 영어 표현들을 익히는 것은 매우 중요합니다. '
                WHEN n % 5 = 4 THEN '올바른 발음 교정을 통해 더 나은 의사소통을 할 수 있는 방법을 알아봅시다. '
                ELSE '영어 문법의 기본을 이해하면 더 정확하게 말할 수 있습니다. '
                END,
            CASE
                WHEN n % 4 = 1 THEN '이 가이드는 초보자들에게 적합하며, 일상에서 쉽게 적용할 수 있는 방법들을 소개합니다. '
                WHEN n % 4 = 2 THEN '중급 학습자들이 흔히 저지르는 실수를 피하는 방법에 대해 설명합니다. '
                WHEN n % 4 = 3 THEN '고급 학습자들을 위한 심화된 학습 방법과 예제를 제공합니다. '
                ELSE '다양한 예제와 함께 실생활에서 사용할 수 있는 영어 팁을 제공합니다. '
                END,
            '이 글의 목표는 여러분이 영어를 보다 쉽게 이해하고 사용할 수 있도록 돕는 것입니다. ',
            '실전 예제로는, "Can I get a glass of water?"와 같은 간단한 문장을 사용하는 방법을 다룹니다. ',
            '지금부터 영어 실력을 향상시키기 위한 다양한 전략을 살펴보겠습니다. 예제 번호: ', CAST(n AS CHAR)
        ) AS content,

    'https://www.youtube.com/watch?v=a57hvjknIcQ' AS youtube_url,

    TIMESTAMP(DATE_ADD(NOW(), INTERVAL -FLOOR(RAND(n) * 1825) DAY) + INTERVAL FLOOR(RAND(n) * 86400) SECOND) AS created_at
FROM cte;
