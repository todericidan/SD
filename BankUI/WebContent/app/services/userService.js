(function() {
	var userServices = angular.module('userServices', []);

	userServices.factory('UserFactory', [ '$http', 'config',
		function($http, config) {

		function findUser(username) {
			return $http.get(config.API_URL + '/employee/' + username);

		};
		
		function findLogsOfUserFiltered(username,startdate,stopdate){
			return $http.get('http://localhost:8080/bankapp/log/'+username+'/'+startdate+'/'+stopdate)
		    .then(function (response) {
		    	console.log(response);
				return  response;
			})
			.catch(function (data, status) {
			});

		};

		
		function login(username,password) {
			return $http.post('http://localhost:8080/bankapp/employee/login/' + username+'/'+password)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
			});
		};

		function findAll() {
			return $http.get(config.API_URL + '/employee');
		};

		function addUser(user) {
			return $http.post(config.API_URL+'/employee', user)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
			});
		};

		function updateUser(user, username) {
			return $http.put('http://localhost:8080/bankapp/employee/' + username, user)
			.then(function (response) {
				response.data;
			})
			.catch(function (data, status) {
			});
		};

		function deleteUser(username) {
			return $http.delete(config.API_URL + '/employee/' + username)
			.then(function (response) {
				response.data;
			}).catch(function (data, status) {
			});
		};
		
		function addLog(log) {
			return $http.post('http://localhost:8080/bankapp/log', log)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
				console.log(log);
				console.log(data);
			});
		};
		
		function findClient(ssn) {
			return $http.get(config.API_URL + '/client/' + ssn);

		};
		
		function findAllClients() {
			return $http.get(config.API_URL + '/client');
		};
		
		
		function updateClient(client, ssn) {
			return $http.put('http://localhost:8080/bankapp/client/' + ssn, client)
			.then(function (response) {
				response.data;
			})
			.catch(function (data, status) {
			});
		};

		function deleteClient(ssn) {
			return $http.delete(config.API_URL + '/client/' + ssn)
			.then(function (response) {
				response.data;
			}).catch(function (data, status) {
			});
		};
		
		
		function addClient(client) {
			//console.log("aici ");
			console.log(client);
			return $http.post(config.API_URL+'/client', client)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
			});
		};
		
		
		function addAccount(account) {
			console.log("aici ");
			console.log(account);
			return $http.post(config.API_URL+'/account', account)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
			});
		};
		
		function findAccount(id) {
			return $http.get(config.API_URL + '/account/' + id);
		};
		
		function findTransactions(id) {
			return $http.get(config.API_URL + '/trans/'+id);
		};
		
		function deleteAccount(id) {
			return $http.delete(config.API_URL + '/account/' + id)
			.then(function (response) {
				response.data;
			}).catch(function (data, status) {
			});
		};
		
		function updateAccount(account, id) {
			return $http.put('http://localhost:8080/bankapp/account/' + id, account)
			.then(function (response) {
				response.data;
			})
			.catch(function (data, status) {
			});
		};
		
		
		function addTransfer(newTransfer,idFrom,idTo) {
			//console.log("aici ");
			return $http.post(config.API_URL+'/trans/transfer/'+idFrom+'/'+idTo, newTransfer)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
			});
		};
		
		function payUtility(newBill) {
			return $http.post(config.API_URL+'/trans/payBill',newBill)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
			});
		};
		
		function singleTransfer(newTrans) {
			return $http.post(config.API_URL+'/trans/simpletransaction',newTrans)
			.then(function (response) {
				return  response.data;
			})
			.catch(function (data, status) {
			});
		};

		
//		var username='none';
//		
//		function setUsername(name){
//			username = name;
//		};
//		
//		function getUsername(){
//			return username;
//		};


		return {
			findUser :findUser,
			findLogsOfUserFiltered:findLogsOfUserFiltered,
			login:login,
			findAll : findAll,
			addUser: addUser,
			updateUser:updateUser,
			deleteUser:deleteUser,
			addLog:addLog,
			findClient:findClient,
			findAllClients:findAllClients,
			updateClient:updateClient,
			deleteClient:deleteClient,
			addClient:addClient,
//			setUsername:setUsername,
//			getUsername:getUsername,
			addAccount:addAccount,
			findAccount:findAccount,
			findTransactions:findTransactions,
			deleteAccount:deleteAccount,
			updateAccount: updateAccount,
			addTransfer:addTransfer,
			payUtility:payUtility,
			singleTransfer:singleTransfer
		};
	} ]);

})();