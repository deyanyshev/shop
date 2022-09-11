import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Injectable()
export class FileService {

  constructor(private http: HttpClient) {
  }

  public uploadFile(dataurl: any, filename: any, format: any) {
    const arr = dataurl.split(',');
    const mime = arr[0].match(/:(.*?);/)[1];
    const bstr = atob(arr[1]);
    let n = bstr.length;
    const u8arr = new Uint8Array(n);
    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }
    saveAs(new File([u8arr], filename, {type: format}));
  }
}
