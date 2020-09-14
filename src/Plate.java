import java.util.Random;

public class Plate {
    private int[][] mines;//지뢰의 매설정보
    public int minesnum;
    private boolean firstdig = true;
    public Plate(int r, int c, int minesnum) throws Exception {//가로, 세로, 지뢰의 개수
        //가로 세로는 모두 10이상 가로*세로-9보다 지뢰의 개수가 작아야함
        if(r<10||c<10){
            throw new Exception("가로 세로는 모두 10이상 이여야함\nr: "+r+"\nc: "+c);
        }
        if(r*c-9<=minesnum){
            throw new Exception("지뢰를 배열할 칸이 부족합니다\nr*c-9: "+(r*c-9)+"\nminesnum: "+minesnum);
        }
        mines = new int[r][c];//배열 생성
        /* ┌─────c개─────┐
         ┌{{0, 0, ..., 0},
         │{0, 0, ..., 0},
         │{0, 0, ..., 0},
       r개 ....
         │....
         │....
         └{0, 0, ..., 0}}
         인데, (x, y)형식이 편해서 이렁게 했다.
           */
        this.minesnum = minesnum;
    }
    public boolean dig(int x, int y){
        if(firstdig){
            firstdig(x, y);
        }else{

        }
        return true;//지뢰 터짐
    }
    public void firstdig(int x, int y){
        firstdig = false;
        Random random = new Random();//랜덤 생성
        for (int i = 0; i < minesnum; i++) {
            int minesx = random.nextInt(mines.length);//가로의 지뢰
            int minesy = random.nextInt(mines[0].length);
            if(mines[minesx][minesy]!=-1&&(Math.abs(x-1-minesx)>=3||Math.abs(y-1-minesy)>=3)){//만약 지뢰가 없다면&판곳 주변이 아니라면(시작하자마자 폭발&찍기 방지)
                mines[minesx][minesy]=-1;//지뢰로 설정
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        try {
                            if (mines[minesx + j][minesy + k] >= 0) {
                                mines[minesx + j][minesy + k]++;//지뢰가 없는칸이라면 주위카운트++
                            }
                        }catch (ArrayIndexOutOfBoundsException e){}//끝쪽이면 예외처리
                    }
                }
            }else i--;
        }
    }
    public void printmines(){
        for (int[] c:mines) {
            for (int k:c) {
                System.out.print(k+" ");
            }
            System.out.println();
        }
    }
}
