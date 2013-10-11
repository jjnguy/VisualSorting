
var DataGenerator = function(){
	var self = this;

	self.random = function(length, maxRandomValue){
		var arr = new Array(length);
		for(var i = 0; i < arr.length; i++){
			arr[i] = getRandomInt(0, maxRandomValue || 100);
		}
		return arr;
	};
	
	self.sorted = function(length){
		var arr = new Array(length);
		for(var i = 0; i < arr.length; i++){
			arr[i] = i;
		}
		return arr;
	};
	
	self.reversed = function(length){
		var arr = new Array(length);
		for(var i = 0; i < arr.length; i++){
			arr[i] = length - i;
		}
		return arr;
	};
	
	self.almostSorted = function(length, swaps){
		var arr = self.sorted(length);
		for(var i = 0; i < swaps; i++){
			var rdm1 = getRandomInt(0, length);
			var rdm2 = getRandomInt(0, length);
			var temp = arr[rdm1];
			arr[rdm1] = arr[rdm2];
			arr[rdm2] = temp;
		}
		return arr;
	};
	
	self.almostSortedClose = function(length, swaps, maxDistance){
		var arr = self.sorted(length);
		for(var i = 0; i < swaps; i++){
			var rdm1 = getRandomInt(0, length);
			var rdm2 = getRandomInt(Math.max(rdm1 - maxDistance, 0), Math.min(rdm1 + maxDistance, length - 1));
			var temp = arr[rdm1];
			arr[rdm1] = arr[rdm2];
			arr[rdm2] = temp;
		}
		return arr;
	};
}

// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/random
// Returns a random integer between min and max
// Using Math.round() will give you a non-uniform distribution!
function getRandomInt(min, max) {
	return Math.floor(Math.random() * (max - min + 1) + min) || 0;
}
