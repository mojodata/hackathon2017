import { Injectable } from '@angular/core';

@Injectable()
export class MapColorService {

	private rankColorMap = [
	/*
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
	*/

/*
		'#6AADE4',
		'#0051A5',
		'#B8A970',
		'#008500',
		'#EED9D8',
		'#F93F26',
		'#FEDF01',
		'#FCA311',
		'#D6BDDD',
		'#5D3566',
		'#E486E4',
		'#FFC0CB'
*/

/*
		'#000512',
		'#002274',
		'#003FD6',
		'#1258FF',
		'#2666FF',
		'#4D81FF',
		'#749DFF',
		'#9CB9FF',
		'#C3D5FF',
		'#EAF0FF',
		'#EAF0FF',
		'#EAF0FF'
*/

		'#1258FF',
		'#3A74FF',
		'#4D81FF',
		'#618FFF',
		'#749DFF',
		'#88ABFF',
		'#9CB9FF',
		'#AFC7FF',
		'#D6E2FF',
		'#EAF0FF'
	];

  constructor() { }

  getColorCodeByRank(rank: number): string {
  	return this.rankColorMap[rank - 1];
  }

  getColorRankArray(): Array<string> {
  	return this.rankColorMap;
  }

}
