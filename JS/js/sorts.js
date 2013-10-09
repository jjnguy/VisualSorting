
var Sorter = function(arr, sortFunction){
	var self = this;
	var callbacks = [];
	
	self.sort = function(){
		sortFunction(arr, indicateProgress, setCurrentIndexes);
	}
	
	self.length = function(){
		return arr.length;
	};
	
	self.addProgressCallback = function(callback){
		callbacks.push(callback);
	};
	
	self.currentIdexOperatedOn = function(){
		return indexes;
	};
	
	self.get = function(i){
		return arr[i];
	}
	
	function indicateProgress(){
		for(var i = 0; i < callbacks.length; i++){
			callbacks[i]({
				arr : arr.slice(),
				indexes : indexes
			});
		}
	}
	
	var indexes = [];
	function setCurrentIndexes(){
		indexes = arguments;
	}
	
};

function insertionSort(arr, indicateProgress, setCurrentIndexes){
	for (var i = 1; i < arr.length; i++) {
		setCurrentIndexes(i);
		var valToInsert = arr[i];
		var holePos = i;
		while (holePos > 0 && valToInsert < arr[holePos - 1]) {
			setCurrentIndexes(holePos, i);
			arr[holePos] = arr[holePos - 1];
			holePos--;
			indicateProgress();
		}
		arr[holePos] = valToInsert;
	}
}
var qSorter = {
	sort : function (arr, indicateProgress, setCurrentIndexes){
		sortInternal(0, arr.length, arr, indicateProgress, setCurrentIndexes);
	},
	sortInternal : function(start, end, arr, indicateProgress, setCurrentIndexes){
		if (Math.abs(start - end) <= 1) {
			return;
		}
		int pivIdx = partition(start, end - 1, arr, indicateProgress, setCurrentIndexes);
		qSorter.sortInternal(start, pivIdx, arr, indicateProgress, setCurrentIndexes);
		qSorter.sortInternal(pivIdx, end, arr, indicateProgress, setCurrentIndexes);
	},
	partition : function(start, end, arr, indicateProgress, setCurrentIndexes) {
		var partitionIndex = pickPivotIndex(start, end);
		var partitionValue = arr[partitionIndex];
		swap(partitionIndex, end);
		var high = end - 1;
		var low = start;
		setCurrentIndexes(low, high);
		while (low <= high) {
			if (get(low) > partitionValue) {
				swap(low, high--);
			} else if (get(high) < partitionValue) {
				swap(low++, high);
			} else {
				low++;
				high--;
			}
			indicateProgress();
			setCurrentIndexes(low, high);
		}
		return (low);
	},
	pickPivotIndex : function(start, end) {
		return start;
	}
}
