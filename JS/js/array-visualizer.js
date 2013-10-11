
var ArrayVisualizer = function(sorter, canvas){
	// stole some ideas for drawing from the following:
	// http://joshuakehn.com/blog/static/sort.html
	var self = this;
	var ctx = canvas.getContext( '2d' );
	
	var arraysToPaint = [];
	
	self.addFrame = function(frameData){
		arraysToPaint.push(frameData);
	};
	
	self.paint = function(){
		if (arraysToPaint.length == 0){
			return;
		}
		var data = arraysToPaint.shift();
		var arr = data.arr;
		var indexess = data.indexes;
		var maxVal = max(arr);
		var scale = canvas.height / maxVal;
		canvas.width = canvas.width;
		for(var i = 0; i < arr.length; i++){
			var itemWidth = canvas.width / arr.length;

			ctx.fillStyle = 'red';
			if (arrayContains(indexess, i)) {
				ctx.fillStyle = 'green';
			}
			ctx.fillRect(itemWidth * i, canvas.height, itemWidth, -(scale * arr[i]));
		}
		ctx.fillStyle = 'black';

		//g2.drawString("Name: " + sorter.sortName(), 5, 20);
		//g2.drawString("Time: " + sorter.time(), 5, 40);
		//g2.drawString("Reads: " + sorter.readCount(), 5, 60);
		//g2.drawString("Writes: " + sorter.writeCount(), 5, 80);
	};
	
	function arrayContains(arr, search) {
		for (var i = 0; i < arr.length; i++) {
			if (arr[i] == search) {
				return true;
			}
		}
		return false;
	}
	
	function max(arr){
		var max = -101010;
		for(var i = 0; i < arr.length; i++){
			if (arr[i] > max) max = arr[i];
		}
		return max;
	}
	
	var FPS = 60 * 20;
	setInterval(self.paint, 1000 / FPS);
}
