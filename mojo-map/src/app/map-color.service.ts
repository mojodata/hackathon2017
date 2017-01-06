import { Injectable } from '@angular/core';

@Injectable()
export class MapColorService {

	private rankColorMap = [
		'#002888',
		'#002E9C',
		'#0034AF',
		'#0039C3',
		'#003FD6',
		'#0045EA',
		'#004BFE',
		'#2666FF',
		'#3A74FF',
		'#4D81FF',
		'#618FFF',
		'#749DFF'
	];

  constructor() { }

  getColorCodeByRank(rank: number): string {
  	return this.rankColorMap[rank];
  }

}
