# Request/response examples:
Сценарий 1:
Приложение принимает твою среднюю зарплату за 12 месяцев
и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику:
GET http://localhost:8095/api/vocation-pay/calculate
{
"averagePayment": 10000,
"vacationDays": 16
}

Сценарий 2: 
При запросе также можно указать точные дни ухода в отпуск, 
тогда должен проводиться рассчет отпускных с учётом праздников и выходных:
GET http://localhost:8095/api/vocation-pay/calculate
{
"averagePayment": 1000000,
"startDate": "09/10/2023",
"endDate": "20/10/2023"
}


