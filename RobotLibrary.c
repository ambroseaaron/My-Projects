/**************************************************
* Names: Aaron Ambrose, Andy P
* Program: Phase 1 for robots in library
* Description: Program takes a room number and a path and saves the path as a linked list for that room. It then
               tells you the fast path to the room, and also the reverse path back to the beginning.
* Date: 4/10/2015
*************************************************/
#include <stdio.h>
#include <stdlib.h>

struct PathNodeStruct {
  int CurrentPathMovement;
  struct PathNodeStruct * prev;
  struct PathNodeStruct * next;
};
typedef struct PathNodeStruct PathNode;
typedef struct PathNodeStruct * PathNodePtr;

struct RoomNodeStruct {
  int roomNumber;
  PathNode * pathStart;
  struct RoomNodeStruct * next;
};
typedef struct RoomNodeStruct RoomNode;
typedef struct RoomNodeStruct * RoomNodePtr;

void printListRoom( RoomNodePtr startPtr );
void insertroom(RoomNodePtr *startPtr, int value, char path[]);
void insertpath(PathNodePtr startPtr, char path[]);

int main()
{
  PathNodePtr pathPtr = NULL;
  RoomNodePtr startPtr = NULL;
  int roomNumber = 0;
  char pathtoroom[300];

  do
    {
    printf("Enter a room number\n");
    scanf("%d", &roomNumber);
    RoomNodePtr currentPtr;

    for (currentPtr = startPtr; currentPtr != NULL; currentPtr = currentPtr->next) //traverses through the linked list of rooms
    {
        if(currentPtr->roomNumber == roomNumber) //if the room number just inputted matches one already in the linked list, this is printed
        {
            printf("Path already created for room %d\n", roomNumber);
            roomNumber = -2;
        }
    }
    if(roomNumber!=-1 && roomNumber != -2) //else we will ask for the path to the new room
    {
        printf("Enter path to room\n");
        scanf("%s" , pathtoroom);
        insertroom(&startPtr, roomNumber,pathtoroom);

    }
    } while ( roomNumber != -1 );
  
  return 0;
}

void printListRoom(RoomNodePtr startPtr)
{
    RoomNodePtr currentPtr;

    for (currentPtr = startPtr; currentPtr != NULL; currentPtr = currentPtr->next)
    {
        printf("Path to room %d: ", currentPtr->roomNumber);
    }
}

void insertroom(RoomNodePtr *startPtr, int value, char path[])
{
    RoomNodePtr newNodePtr; /* will point to newly created node */
    RoomNodePtr beforeNodePtr, afterNodePtr;

    /* dynamically allocate memory for new node */
    newNodePtr = (RoomNodePtr) malloc(sizeof(RoomNode)); //allocates memory

    /* insert node in list */
    if (newNodePtr != NULL) /* if successfully allocated memory */
    {
        newNodePtr->roomNumber = value;
        newNodePtr->next = NULL;

        /* determine where node should be inserted    */
        /* by defining beforeNodePtr and afterNodePtr */
        beforeNodePtr = NULL;
        afterNodePtr = *startPtr;
        while (afterNodePtr != NULL)
        {
            beforeNodePtr = afterNodePtr;
            afterNodePtr = afterNodePtr->next;
        }

        /* case 1: insert at beginning of list (beforeNodePtr is still NULL) */
        if (beforeNodePtr == NULL)
        {
            *startPtr = newNodePtr;       /* set new value for the startPtr */
        }
        else /* case 2: insert at end of list */
        {
            beforeNodePtr->next = newNodePtr;
            newNodePtr->next = NULL;
        }

        printListRoom(newNodePtr);

        insertpath(newNodePtr->pathStart, path);
    }
    else
    {
        printf("Error allocating memory for new node of linked list.\n");
    }
}

////////////////////////////////////////

void insertpath(PathNodePtr startPtr, char path[])
{
    int i = 0;
    PathNodePtr newNodePtr; /* will point to newly created node */
    PathNodePtr beforeNodePtr, afterNodePtr;

    while(path[i] != '\0')
    {
        /* dynamically allocate memory for new node */
        newNodePtr = (PathNodePtr) malloc(sizeof(PathNode));

        /* insert node in list */
        if (newNodePtr != NULL) /* if successfully allocated memory */
        {
            newNodePtr->CurrentPathMovement = path[i];
            newNodePtr->next = NULL;

            /* determine where node should be inserted    */
            /* by defining beforeNodePtr and afterNodePtr */
            beforeNodePtr = NULL;
            afterNodePtr = startPtr;
            while (afterNodePtr != NULL)
            {
                beforeNodePtr = afterNodePtr;
                afterNodePtr = afterNodePtr->next;
            }

            /* case 1: insert at beginning of list (beforeNodePtr is still NULL) */
            if (beforeNodePtr == NULL)
            {
                startPtr = newNodePtr;       /* set new value for the startPtr */
            }
            else /* case 2: insert in middle or at end of list */
            {
                beforeNodePtr->next = newNodePtr;
                newNodePtr->next = NULL;
            }
        }
        else
        {
            printf("Error allocating memory for new node of linked list.\n");
        }
        i++;
    }

    PathNodePtr currentPtr, currentPtr2, nextnode, currentnode, deletingnode, endnode, reversepath;

    i = 0;
    int a = 0;

    for (currentPtr = startPtr; currentPtr != NULL; currentPtr = currentPtr->next)//goes through the list to see how many characters there are
    {
        printf("%c", currentPtr->CurrentPathMovement);
        a = a + 1;
    }
    printf("\n");

    printf("Reverse Path: ");

    for(i = a; i>=0; i--) //goes through for how many characters there were
    {
        reversepath = (PathNodePtr) malloc(sizeof(PathNodePtr)); //allocates memory
        reversepath->CurrentPathMovement = path[i]; //sets the node to be the character
        if(reversepath->CurrentPathMovement == 'l') //changes the r's to l's and vise versa
        {
            reversepath->CurrentPathMovement = 'r';
        }
        else if(reversepath->CurrentPathMovement == 'r')
        {
            reversepath->CurrentPathMovement = 'l';
        }
        printf("%c", reversepath->CurrentPathMovement); //prints the changed movement
        reversepath->next = NULL; //sets the next node to null
        reversepath = reversepath->next; //advances to the next node
    }

    printf("\n");
    printf("Simplified Path: ");

    nextnode=NULL;

    for (currentnode = startPtr; currentnode != NULL; currentnode = currentnode->next) //goes through for every path node
    {
        if(currentnode->next != NULL) //if the next node is filled
        {
            deletingnode = currentnode->next; //set a node to equal the next node

            if(deletingnode->next != NULL) //if the node after that is not filled
            {
                nextnode = deletingnode->next; //set a node to equal the next node

                if(currentnode->CurrentPathMovement == 'l' && deletingnode->CurrentPathMovement == 'u' && nextnode->CurrentPathMovement == 'l')
                {
                    currentnode->CurrentPathMovement = 's'; //if lul is in path, change it to s
                    if(nextnode->next == NULL) //if the node after lul is null, set the next node to be null
                    {
                        currentnode->next = NULL;
                    }
                    else
                    {
                        currentnode->next=nextnode->next; //otherwise set the next node to be the node after lul
                        free(deletingnode); //free the u and l node
                        free(nextnode);
                    }
                }
                else if(currentnode->CurrentPathMovement == 'r' && deletingnode->CurrentPathMovement == 'u' && nextnode->CurrentPathMovement == 'r')
                {
                    currentnode->CurrentPathMovement = 's'; //if rur is in path, change it to an s
                    if(nextnode->next = NULL) //if the node after rur is null, set the next node to null
                    {
                        currentnode->next = NULL;
                    }
                    else
                    {
                        currentnode->next=nextnode->next; //else set the next node to be the node after rur
                        free(deletingnode); //free the u and r nodes
                        free(nextnode);
                    }
                }
            }
        }
        printf("%c", currentnode->CurrentPathMovement); //prints the changed n
    }
    printf("\n");
}
