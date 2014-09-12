;(function(undefined) {
  'use strict';

  if (typeof sigma === 'undefined')
    throw 'sigma is not declared';

  // Initialize packages:
  sigma.utils.pkg('sigma.canvas.labels');

  /**
   * This label renderer will just display the label on the right of the node.
   *
   * @param  {object}                   node     The node object.
   * @param  {CanvasRenderingContext2D} context  The canvas context.
   * @param  {configurable}             settings The settings function.
   */
  sigma.canvas.labels.def = function(node, context, settings) {
    var fontSize,
        prefix = settings('prefix') || '',
        size = node[prefix + 'size'];

    if (size < settings('labelThreshold'))
      return;

    if (typeof node.label !== 'string')
      return;

    fontSize = (settings('labelSize') === 'fixed') ?
      settings('defaultLabelSize') :
      settings('labelSizeRatio') * size;

    context.font = (settings('fontStyle') ? settings('fontStyle') + ' ' : '') +
      fontSize + 'px ' + settings('font');
    context.fillStyle = (settings('labelColor') === 'node') ?
      (node.color || settings('defaultNodeColor')) :
      settings('defaultLabelColor');

    node.label = node.label.replace(/_SP_/g, " ");
    var lines = node.label.split('_NL_');
    var lineheight = 15;

    for (var i = 0; i<lines.length; i++){
    	context.fillText(
    	  lines[i],
	      Math.round(node[prefix + 'x'] + size + 3),
	      Math.round(node[prefix + 'y'] + fontSize / 3 + (i*lineheight))
	    );
    }

    if (typeof node.extraLabel !== 'undefined'){
	    context.fillText(
		  node.extraLabel,
	      Math.round(node[prefix + 'x'] - (size + 5)),
	      Math.round(node[prefix + 'y'] - (fontSize / 3) - 10)
	    );
    }
    if (typeof node.summary !== 'undefined'){
	    context.fillText(
		  node.summary,
	      Math.round(node[prefix + 'x'] - (size + 5)),
	      Math.round(node[prefix + 'y'] + (fontSize / 3) + 80)
	    );
    }

  };
}).call(this);
