
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

function bubbleSort(arr, indicateProgress, setCurrentIndexes){
	var swappedIdx = arr.length;
	var swapped = false;
	do {
		var swapIdxThisIteration = swappedIdx;
		swapped = false;
		for (var i = 0; i < swapIdxThisIteration - 1; i++) {
			setCurrentIndexes(i, i+1);
			if (arr[i] > arr[i + 1]) {
				swap(arr, i, i + 1);
				swappedIdx = i + 1;
				swapped = true;
			}
			indicateProgress();
		}
	} while (swapped);
}

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
	sortSmart : function (arr, indicateProgress, setCurrentIndexes){
		qSorter.sortInternal(0, arr.length, arr, indicateProgress, setCurrentIndexes, qSorter.pickPivotIndexSmart);
	},
	sortDumb : function (arr, indicateProgress, setCurrentIndexes){
		qSorter.sortInternal(0, arr.length, arr, indicateProgress, setCurrentIndexes, qSorter.pickPivotIndexDumb);
	},
	sortInternal : function(start, end, arr, indicateProgress, setCurrentIndexes, pivotStrategy){
		if (Math.abs(start - end) <= 1) {
			return;
		}
		var pivIdx = qSorter.partition(start, end - 1, arr, indicateProgress, setCurrentIndexes, pivotStrategy);
		qSorter.sortInternal(start, pivIdx, arr, indicateProgress, setCurrentIndexes, pivotStrategy);
		qSorter.sortInternal(pivIdx, end, arr, indicateProgress, setCurrentIndexes, pivotStrategy);
	},
	partition : function(start, end, arr, indicateProgress, setCurrentIndexes, pivotStrategy) {
		var partitionIndex = pivotStrategy(start, end, arr);
		var partitionValue = arr[partitionIndex];
		swap(arr, partitionIndex, end);
		var high = end - 1;
		var low = start;
		setCurrentIndexes(low, high);
		while (low <= high) {
			if (arr[low] > partitionValue) {
				swap(arr, low, high--);
			} else if (arr[high] < partitionValue) {
				swap(arr, low++, high);
			} else {
				low++;
				high--;
			}
			indicateProgress();
			setCurrentIndexes(low, high);
		}
		return (low);
	},
	pickPivotIndexDumb : function(start, end, arr) {
		return start;
	},
	pickPivotIndexSmart : function(start, end, arr){
		if (start - end > 2) {
			var middle = arr[intDiv(start + end, 2)];
			var first = arr[start];
			var last = arr[end - 1];

			if (first > middle) {
				if (middle > last) {
					return middle;
				} else if (first > last) {
					return last;
				} else {
					return first;
				}
			} else {
				if (first > last) {
					return first;
				} else if (middle > last) {
					return last;
				} else {
					return middle;
				}
			}
		}
		return intDiv(start + end, 2);
	}
}

var mergeSorter = {
	sort : function(arr, indicateProgress, setCurrentIndexes){
		mergeSorter.sortInternal(0, arr.length, arr, indicateProgress, setCurrentIndexes);
	},
	sortInternal : function(start, end, arr, indicateProgress, setCurrentIndexes){
		if (end - start < 2)
			return;

		var middle = intDiv(end + start, 2);

		mergeSorter.sortInternal(start, middle, arr, indicateProgress, setCurrentIndexes);
		mergeSorter.sortInternal(middle, end, arr, indicateProgress, setCurrentIndexes);

		mergeSorter.merge(start, middle, end, arr, indicateProgress, setCurrentIndexes);
	},
	merge : function(firstStart, middle, secondEnd, arr, indicateProgress, setCurrentIndexes){
		var idxOne = firstStart;

		setCurrentIndexes(firstStart, secondEnd, middle, idxOne);
		while (idxOne < middle && middle < secondEnd) {
			setCurrentIndexes(firstStart, middle, secondEnd, idxOne);
			if (arr[idxOne] > arr[middle]) {
				// move two in front of one, and shift the rest back
				var tempVal = arr[middle];
				for (var i = middle; i > idxOne; i--) {
					arr[i] = arr[i-1];
				}
				arr[idxOne] = tempVal;
				middle++;
			}
			idxOne++;
			indicateProgress();
		}
	}
}

function intDiv(i, j){
	return Math.floor(i / j);
}

function swap(arr, i, j){
	var temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}
