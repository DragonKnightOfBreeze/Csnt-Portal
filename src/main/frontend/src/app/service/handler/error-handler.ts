import {Observable, of} from "rxjs";

/**
 * 处理Http操作错误，让程序继续运行。
 * @param operation 失败的操作的名字
 * @param result 需要返回的可观察对象结果
 */
export function handleError<T>(operation = 'operation', result ?: T) {
  return (error: any): Observable<T> => {
    //打印错误信息
    console.log(error);
    //通过返回一个空结果让程序得以继续运行
    return of(result as T);
  };
}
