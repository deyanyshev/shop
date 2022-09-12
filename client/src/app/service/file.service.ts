import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Injectable()
export class FileService {

  constructor(private http: HttpClient) {
  }

  public uploadFile(file: File, name: string) {
    const formData = new FormData();
    formData.append('img', file, name);
    return this.http.post('/api/products/add-img', formData);
  }

}
