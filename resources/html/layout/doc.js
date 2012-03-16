function addToC(target) {
	target = $(target);
	var headings = $$("[class=toc]");
	if(headings == null || headings.length < 1) {
		return;
	}
	
	var lvl = 1;
	var list = new Element("ul");
	var item = null;
	target.appendChild(list);
	for(var i = 0, l = headings.length; i < l; i++) {
		var heading = $(headings[i]);
		var thisLvl = 0;
		switch(heading.nodeName) {
			case "H1": thisLvl = 1; break;
			case "H2": thisLvl = 2; break;
			case "H3": thisLvl = 3; break;
			case "H4": thisLvl = 4; break;
			case "H5": thisLvl = 5; break;
			case "H6": thisLvl = 6; break;
		}
		
		if(thisLvl > lvl && item != null) {
			var list = new Element("ul");
			item.appendChild(list);
		} else if(thisLvl < lvl) {
			list = list.parentNode;
		}
		lvl = thisLvl;
		var label = heading.get("html");
		var anchor = label.replace(/\W/g, "");
		
		new Element("a", {name: anchor}).inject(heading, "before");
		item = new Element("li", {html: '<a href="#'+anchor+'">'+label+'</a>'});
		list.appendChild(item);
	}
}