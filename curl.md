
##### Get user by email
`curl -i -s "http://localhost:8080/topjava/rest/admin/users/by?email=admin@gmail.com"`

##### Delete user
`curl -i -s -X DELETE "http://localhost:8080/topjava/rest/admin/users/100000"`

##### Create new user
`curl -i -X POST -H "Content-Type: application/json" -d '{"name":"User2","email":"user2@gmail.com","password":"admin","enabled":true,"registered":"2018-04-24T04:25:13.577+0000","roles":["ROLE_USER"],"caloriesPerDay":2000,"meals":null}' "http://localhost:8080/topjava/rest/admin/users"`

##### Update user
`curl -i -X PUT -H "Content-Type: application/json" -d '{"id":100001,"name":"Admin","email":"admin@gmail.com","password":"admin","enabled":true,"registered":"2018-04-24T04:25:13.577+0000","roles":["ROLE_ADMIN"],"caloriesPerDay":2000,"meals":null}' "http://localhost:8080/topjava/rest/admin/users/100001"`

##### Get meals
`curl -i -s "http://localhost:8080/topjava/rest/meals"`

##### Get meal by id
`curl -i -s "http://localhost:8080/topjava/rest/meals/100005"`

##### Get filtered meals
`curl -i -s "http://localhost:8080/topjava/rest/meals/filter?startTime=13:00&endDate=2015-05-30"`

##### Delete meal by id
`curl -i -s -X DELETE "http://localhost:8080/topjava/rest/meals/100005"`

##### Update meal
`curl -i -X PUT -H "Content-Type: application/json" -d '{"id":100006,"dateTime":"2017-06-01T21:30:00","description":"Ужин","calories":1500}' "http://localhost:8080/topjava/rest/meals/100006"`

##### Create new meal
`curl -i -X POST -H "Content-Type: application/json" -d '{"dateTime":"2016-06-01T21:10:00","description":"Ужин","calories":1500}' "http://localhost:8080/topjava/rest/meals"`
