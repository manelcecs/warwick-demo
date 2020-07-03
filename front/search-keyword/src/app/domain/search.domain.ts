//Data type for the api response
export interface SearchResponse {
    numberOccurrences: number;
    timeElapsed: number;
}

//Data type for api request
export interface SearchRequest {
    keyWord: string;
    text: string;
}