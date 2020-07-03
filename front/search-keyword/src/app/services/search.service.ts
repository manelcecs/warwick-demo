import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { SearchRequest, SearchResponse } from '../domain/search.domain';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class SearchService{

    //backend uri
    private splitURI: string = "api/search/split";
    private frecuencyURI: string = "api/search/frecuency";

    private backendURI: string = environment.backendUri;

    //Costructor of the service
    constructor(private httpClient: HttpClient)
    {    }

    sendRequestToSplit(request: SearchRequest): Observable<SearchResponse>{
        return this.httpClient.post<SearchResponse>(this.backendURI + this.splitURI, request);
    }

}