import { NowRequest, NowResponse } from '@vercel/node'
import { Random } from "mockjs";


export default (request: NowRequest, response: NowResponse) => {
  const token = Random.guid();
  response.status(200).send(token);
}
