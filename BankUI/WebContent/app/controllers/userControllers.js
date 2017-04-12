(function() {

	var usersModule = angular.module('userControllers', [ 'ngRoute' ])

	usersModule.config(function($routeProvider) {
		$routeProvider.when('/users', {
			title:"users",
			templateUrl : 'app/views/user/user-list.html',
			controller : 'AllUsersController',
			controllerAs : "allUsersCtrl"
		}).when('/users/:username', {
			templateUrl : 'app/views/user/user-details.html',
			controller : 'UserController',
			controllerAs : "userCtrl"
		}).when('/user/register', {
			templateUrl : 'app/views/user/register-user.html',
			controller : 'RegisterUserController',
			controllerAs : "registerUserCtrl"
		}).when('/login', {
			templateUrl : 'app/views/user/login.html',
			controller : 'LoginController',
			controllerAs : "loginCtrl"
		}).when('/clients', {
			title:"clients",
			templateUrl : 'app/views/user/clients.html',
			controller : 'AllClientsController',
			controllerAs : "allClientsCtrl"
		}).when('/clients/:ssn', {
			templateUrl : 'app/views/user/client-details.html',
			controller : 'ClientController',
			controllerAs : "clientCtrl"
		}).when('/client/register', {
			templateUrl : 'app/views/user/register-client.html',
			controller : 'RegisterClientController',
			controllerAs : "registerClientCtrl"
		}).when('/client/:ssn/addAccount', {
			templateUrl : 'app/views/user/register-account.html',
			controller : 'RegisterAccountController',
			controllerAs : "registerAccountCtrl"
		}).when('/account/:id', {
			templateUrl : 'app/views/user/account-details.html',
			controller : 'AccountController',
			controllerAs : "accountCtrl"
		})

	});

	usersModule.controller('AllUsersController', [ '$scope', 'UserFactory',
		function($scope, UserFactory) {
		var vm = this;
		vm.showLogs=false;
		vm.showLogAlert=false;


		vm.users = [];
		var promise = UserFactory.findAll();

		promise.success(function(data) {
			vm.users = data;
			console.log(vm.users);


		}).error(function(data, status, header, config) {
			alert(status);
		});

		vm.searchLogs=searchLogs;
		function searchLogs(){
			console.log("logs fetch");
			console.log(vm.username);
			console.log(vm.startdate);
			console.log(vm.stopdate)

			//UserFactory.updateUser(vm.user, username) ;

			var promiselog = UserFactory.findLogsOfUserFiltered(vm.username,vm.startdate,vm.stopdate);
			promiselog.then(function(response){
				console.log(response);
				vm.logs = response.data;
				if(response.data){
					vm.showLogs=true;
					vm.showLogAlert=false;
				}
				else{

					vm.showLogs=false;
					vm.showLogAlert=true;
				}
			});

			console.log(vm.logs);

			//vm.logsTable.reload();

		};


	} ]);


	usersModule.controller('UserController', [ '$scope', '$routeParams','UserFactory', '$location',
		function($scope, $routeParams, UserFactory,$location) {
		var vm = this;

		var username = $routeParams.username;
		console.log(username);
		vm.user = null;

		var promise  = UserFactory.findUser(username) ;

		promise.success(function(data) {
			vm.user = data;
			console.log(vm.user);
		}).error(function(data, status, header, config) {
			alert(status);
		});

		vm.deleteAction=deleteAction;
		function deleteAction(){
			console.log("delete user");
			console.log(username);
			UserFactory.deleteUser(username) ;
			$location.path('/users'); 

		}

		vm.updateAction=updateAction;
		function updateAction(){
			console.log("update user");
			console.log(username);
			console.log(vm.user);
			UserFactory.updateUser(vm.user, username) ;
			$location.path('/users'); 
		}

	} ]);



	usersModule.controller('RegisterUserController', [ '$scope','UserFactory', '$location',
		function ($scope, UserFactory,$location) {
		var vm = this;
		vm.submit=submit;
		function submit(){
			console.log("register");
			console.log(vm.user);
			UserFactory.addUser(vm.user);
			$location.path('/users'); 
		}

	}]);

	usersModule.controller('LoginController', [ '$scope','UserFactory','$location',
		function ($scope, UserFactory,$location) {
		var vm = this;
		vm.username="";
		vm.password="";
		vm.log={};

		vm.submit=submit;
		function submit(){

			//console.log("login");
			//console.log("credentials "+vm.username+" "+vm.password);

			var promise = UserFactory.login(vm.username,vm.password);
			promise.then(function(response){

				vm.response = response;

				if(vm.response.username) {
					console.log("good credentials");
					vm.credentials = false;
					
					//UserFactory.setUsername(vm.username);
					//console.log(UserFactory.getUsername);
					
					vm.log.logTime="1";				
					vm.log.username=vm.response.username;
					vm.log.logDescription="User "+vm.log.username+" has logged in";

					//console.log(vm.log);	
					UserFactory.addLog(vm.log);
					console.log(vm.response.isAdmin);
					if(vm.response.isAdmin==1){
						$location.path('/users'); 
					}
					else{
						$location.path('/clients'); 
					}
				}
				else {
					vm.credentials = true;
					console.log("bad credentials");
				}

			})

		}

	}]);

	usersModule.controller('AllClientsController', [ '$scope', 'UserFactory',
		function($scope, UserFactory) {
		var vm = this;

		vm.clients = [];
		var promise = UserFactory.findAllClients();

		promise.success(function(data) {
			vm.clients = data;
			console.log(vm.clients);


		}).error(function(data, status, header, config) {
			alert(status);
		});


	}]);


	usersModule.controller('ClientController', [ '$scope', '$routeParams','UserFactory', '$location',
		function($scope, $routeParams, UserFactory, $location) {
		var vm = this;

		var ssn = $routeParams.ssn;
		console.log(ssn);
		vm.client = null;

		var promise  = UserFactory.findClient(ssn) ;

		promise.success(function(data) {
			vm.client = data;
			console.log(vm.client);
		}).error(function(data, status, header, config) {
			alert(status);
		});

		vm.deleteAction=deleteAction;
		function deleteAction(){
			console.log("delete client");
			console.log(ssn);
			UserFactory.deleteClient(ssn) ;
			$location.path('/clients'); 

		}

		vm.updateAction=updateAction;
		function updateAction(){
			console.log("update client");
			console.log(ssn);
			console.log(vm.client);
			UserFactory.updateClient(vm.client, ssn) ;
			$location.path('/clients'); 
		}

	} ]);


	usersModule.controller('RegisterClientController', [ '$scope','UserFactory', '$location',
		function ($scope, UserFactory,$location) {
		var vm = this;
		vm.submit=submit;
		function submit(){
			console.log("register client");
			vm.client.accountList=[];
			console.log(vm.client);
			UserFactory.addClient(vm.client);
			$location.path('/clients'); 
		}

	}]);


	usersModule.controller('RegisterAccountController', [ '$scope','$routeParams','UserFactory', '$location',
		function ($scope, $routeParams,UserFactory,$location) {
		var vm = this;
		vm.ssn=$routeParams.ssn;
		vm.submit=submit;

		function submit(){
			console.log("register account");
			console.log(vm.ssn);
			vm.account.dateOfCreation='1';
			console.log(vm.acount);
			//UserFactory.addAccount(vm.account);
			//$location.path('/clients'); 
		}

	}]);


	usersModule.controller('AccountController', [ '$scope', '$routeParams','UserFactory', '$location',
		function($scope, $routeParams, UserFactory,$location) {
		var vm = this;
		
		vm.operationTypes=["WITHDRAWAL","DEPOSIT"];

		var id = $routeParams.id;
		console.log(id);
		vm.account = null;
		vm.newTransfer={};
		vm.newBill={};

		var promise  = UserFactory.findAccount(id) ;

		promise.success(function(data) {
			vm.account = data;
			console.log(vm.account);
		}).error(function(data, status, header, config) {
			alert(status);
		});
		
		vm.transactions = null;

		var promise1  = UserFactory.findTransactions(id);

		promise1.success(function(data) {
			vm.transactions = data;
			console.log(vm.transactions);
		}).error(function(data, status, header, config) {
			alert(status);
		});
		
		vm.deleteAction=deleteAction;
		function deleteAction(){
			console.log("delete account");
			
			UserFactory.deleteAccount(id) ;
			$location.path('/clients'); 
		}

		vm.updateAction=updateAction;
		function updateAction(){
			console.log("update account");

			UserFactory.updateAccount(vm.account, id) ;
			$location.path('/clients'); 
		}
		
	
		
		vm.transfer=transfer;
		function transfer(){
			console.log("tranfer money");
			vm.newTransfer.accountID=id;
			vm.newTransfer.operationType="TRANSFER";
			vm.newTransfer.description="Transfer money from account "+vm.newTransfer.accountID+" to account "+vm.id2
			vm.newTransfer.initialBalance=1;
			vm.newTransfer.closingBalance=1;
			vm.newTransfer.creationTime="1";
			console.log(vm.newTransfer);
			console.log(vm.id2);
			UserFactory.addTransfer(vm.newTransfer,vm.newTransfer.accountID,vm.id2) ;
			
			$location.path('/clients'); 
		}
		
		vm.payBill=payBill;
		function payBill(){
			//console.log("tranfer money");
			vm.newBill.accountID=id;
			vm.newBill.operationType="UTILITYBILLPAYMENT";			
			vm.newBill.initialBalance=1;
			vm.newBill.closingBalance=1;
			vm.newBill.creationTime="1";
			
			console.log(vm.newBill);
			
			UserFactory.payUtility(vm.newBill);
			
			$location.path('/clients'); 
		}
		
		vm.singleTrans=singleTrans;
		function singleTrans(){
			
			vm.newTrans.accountID=id;
			vm.newTrans.description=vm.newTrans.operationType+" money ";	
			vm.newTrans.initialBalance=1;
			vm.newTrans.closingBalance=1;
			vm.newTrans.creationTime="1";
			
			console.log(vm.newTrans);
			
			UserFactory.singleTransfer(vm.newTrans);
			
			$location.path('/clients'); 
		}

	} ]);





})();
